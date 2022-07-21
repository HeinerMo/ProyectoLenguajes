using EjemploMVCNetCore2022.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.IO;

namespace IF4101_ProyectoFinal_B87581_B85042.Controllers
{
    public class EventoController : Controller
    {
        public IConfiguration Configuration { get; }

        public EventoController(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IActionResult Registrar()
        {
            return View();
        }

        [HttpPost]
        public IActionResult Registrar(EventoModel eventoModel)
        {
            String e = "2";
            
            var files = HttpContext.Request.Form.Files;
            String p = Path.GetFullPath("wwwroot") + "/";
            var uploads = Path.Combine(p, @"Img");
            var extension = Path.GetExtension(files[0].FileName);
            
            String ruta = eventoModel.Nombre + eventoModel.Recinto + eventoModel.NombreArtista + extension;
            String Inicio = eventoModel.Start_Event.ToString("yyyy-dd-MM HH:mm:ss");
            String Fin = eventoModel.End_Event.ToString("yyyy-dd-MM HH:mm:ss");


            if (ModelState.IsValid)
                {
               try
                {
                    string connectionString = Configuration["ConnectionStrings:DB_Connection"];
                    var connection = new SqlConnection(connectionString);

                    string sqlQuery = $"exec sp_AddEvent @param_Nombre='{eventoModel.Nombre}', @param_Recinto='{eventoModel.Recinto}', @param_Time_Start_Event='{Inicio}',@param_Time_End_Event='{Fin}',@param_Taquilla={eventoModel.Taquilla}, @param_CancionFamosa='{eventoModel.CancionFamosa}', @param_NombreArtista='{eventoModel.NombreArtista}', @param_Imagen='{ruta}'";
                   
                using (SqlCommand command = new SqlCommand(sqlQuery, connection))
                    {
                        command.CommandType = System.Data.CommandType.Text;
                        connection.Open();
                        SqlDataReader productosReader = command.ExecuteReader();
                        while (productosReader.Read())
                        {
                            e = productosReader["ERROR"].ToString();
                        } // while

                        connection.Close();
                    }

                    if (e.Equals("1")) {
                        return View();
                    } else if (e.Equals("0")) {

                        using (var fileStream = new FileStream(Path.Combine(uploads, eventoModel.Nombre + eventoModel.Recinto + eventoModel.NombreArtista + extension), FileMode.Create))
                        {
                            files[0].CopyTo(fileStream);
                        }

                        return Administrar();
                    }
                }
                
                catch (Exception x) {

                    string connectionString2 = Configuration["ConnectionStrings:DB_Connection"];
                    var connection2 = new SqlConnection(connectionString2);

                    string sqlQuery2 = $"INSERT INTO tb_error (ERROR)VALUES('{Inicio}')";

                    using (SqlCommand command = new SqlCommand(sqlQuery2, connection2))
                    {
                        command.CommandType = System.Data.CommandType.Text;
                        connection2.Open();
                        command.ExecuteReader();
                        connection2.Close();
                    }
                
                    return View("Views/Evento/Comentarios.cshtml");
                }
                } // if
                
            
            return Administrar();
        }


        [HttpPost]
        public IActionResult Eliminar(EventoModel eventoModel)
        {

            if (ModelState.IsValid)
            {
                string connectionString = Configuration["ConnectionStrings:DB_Connection"];
                var connection = new SqlConnection(connectionString);

                string sqlQuery = $"exec sp_EliminarEvento @param_Id='{eventoModel.Id}'";

                using (SqlCommand command = new SqlCommand(sqlQuery, connection))
                {
                    command.CommandType = System.Data.CommandType.Text;
                    connection.Open();
                    command.ExecuteReader();
                    connection.Close();
                }



            } // if

            return Administrar();
        }

        public IActionResult Administrar()
        {

            List<EventoModel> eventos = new List<EventoModel>();

            if (ModelState.IsValid)
            {
                string connectionString = Configuration["ConnectionStrings:DB_Connection"];
                using (SqlConnection connection = new SqlConnection(connectionString))
                {
                    string sqlQuery = $"exec sp_ListarEventos";
                    using (SqlCommand command = new SqlCommand(sqlQuery, connection))
                    {
                        command.CommandType = System.Data.CommandType.Text;
                        connection.Open();
                        SqlDataReader productosReader = command.ExecuteReader();
                        while (productosReader.Read())
                        {
                            eventos.Add(new EventoModel(
                                Int32.Parse(productosReader["ID_EVENT"].ToString()),
                                productosReader["NAME_EVENT"].ToString(),
                                productosReader["NAME_RECINTO"].ToString(),
                                DateTime.Parse(productosReader["TIME_START_EVENT"].ToString()),
                                DateTime.Parse(productosReader["TIME_END_EVENT"].ToString()),
                                Int32.Parse(productosReader["TAQUILLA"].ToString()),
                                Int32.Parse(productosReader["ID_ARTIST"].ToString()),
                                productosReader["NAME_ARTIST"].ToString(),
                                productosReader["FAMOUS_SONG"].ToString(),
                                productosReader["PHOTO_ARTIST"].ToString(),
                                productosReader["FINISHED"].ToString()
                                )
                            );
                        } // while
                        connection.Close();
                    } // using
                } // using
            } // if

            ViewBag.Eventos = eventos;

            return View("Views/Evento/Administrar.cshtml");
        }

  

        public IActionResult Comentarios()
        {

            List<ComentarioModel> comentarios = new List<ComentarioModel>();

            if (ModelState.IsValid)
            {
                string connectionString = Configuration["ConnectionStrings:DB_Connection"];
                using (SqlConnection connection = new SqlConnection(connectionString))
                {
                    string sqlQuery = $"exec sp_ListarComentarios";
                    using (SqlCommand command = new SqlCommand(sqlQuery, connection))
                    {
                        command.CommandType = System.Data.CommandType.Text;
                        connection.Open();
                        SqlDataReader comentariosReader = command.ExecuteReader();
                        while (comentariosReader.Read())
                        {
                            comentarios.Add(new ComentarioModel(
                                comentariosReader["student_name"].ToString(),
                                comentariosReader["NAME_ARTIST"].ToString(),
                                comentariosReader["COMMENT"].ToString()
                                )
                            );
                        } // while
                        connection.Close();
                    } // using
                } // using
            } // if

            ViewBag.Comentarios = comentarios;

            return View("Views/Evento/Comentarios.cshtml");
        }

        [HttpPost]
        public IActionResult Buscar(EventoModel eventoModel)
        {
            List<EventoModel> eventos = new List<EventoModel>();

            if (ModelState.IsValid)
            {
                string connectionString = Configuration["ConnectionStrings:DB_Connection"];
                using (SqlConnection connection = new SqlConnection(connectionString))
                {
                    string sqlQuery = $"exec sp_BuscarEventos @param_Nombre='{eventoModel.Nombre}', @param_NombreRecinto='{eventoModel.Recinto}',@param_NombreArtista='{eventoModel.NombreArtista}'";
                    using (SqlCommand command = new SqlCommand(sqlQuery, connection))
                    {
                        command.CommandType = System.Data.CommandType.Text;
                        connection.Open();
                        SqlDataReader productosReader = command.ExecuteReader();
                        while (productosReader.Read())
                        {
                            eventos.Add(new EventoModel(
                                Int32.Parse(productosReader["ID_EVENT"].ToString()),
                                productosReader["NAME_EVENT"].ToString(),
                                productosReader["NAME_RECINTO"].ToString(),
                                DateTime.Parse(productosReader["TIME_START_EVENT"].ToString()),
                                DateTime.Parse(productosReader["TIME_END_EVENT"].ToString()),
                                Int32.Parse(productosReader["TAQUILLA"].ToString()),
                                Int32.Parse(productosReader["ID_ARTIST"].ToString()),
                                productosReader["NAME_ARTIST"].ToString(),
                                productosReader["FAMOUS_SONG"].ToString(),
                                productosReader["PHOTO_ARTIST"].ToString()
                                )
                            );
                        } // while
                        connection.Close();
                    } // using
                } // using
            } // if

            ViewBag.Eventos = eventos;

            return View("Views/Evento/Buscar.cshtml");
        }
        public IActionResult Buscar()
        {
            List<EventoModel> eventos = new List<EventoModel>();
            ViewBag.Eventos = eventos;
            return View("Views/Evento/Buscar.cshtml");
        }


        [HttpPost]
        public IActionResult Actualizar(EventoModel eventoModel)
        {
            List<EventoModel> eventos = new List<EventoModel>();

            if (ModelState.IsValid)
            {
                string connectionString = Configuration["ConnectionStrings:DB_Connection"];
                using (SqlConnection connection = new SqlConnection(connectionString))
                {
                    string sqlQuery = $"exec sp_BuscarEvento @param_Id='{eventoModel.Id}'";
                    using (SqlCommand command = new SqlCommand(sqlQuery, connection))
                    {
                        command.CommandType = System.Data.CommandType.Text;
                        connection.Open();
                        SqlDataReader productosReader = command.ExecuteReader();
                        while (productosReader.Read())
                        {
                            eventos.Add(new EventoModel(
                                Int32.Parse(productosReader["ID_EVENT"].ToString()),
                                productosReader["NAME_EVENT"].ToString(),
                                productosReader["NAME_RECINTO"].ToString(),
                                DateTime.Parse(productosReader["TIME_START_EVENT"].ToString()),
                                DateTime.Parse(productosReader["TIME_END_EVENT"].ToString()),
                                Int32.Parse(productosReader["TAQUILLA"].ToString()),
                                Int32.Parse(productosReader["ID_ARTIST"].ToString()),
                                productosReader["NAME_ARTIST"].ToString(),
                                productosReader["FAMOUS_SONG"].ToString(),
                                productosReader["PHOTO_ARTIST"].ToString()
                                )
                            );
                        } // while
                        connection.Close();
                    } // using
                } // using
            } // if

            ViewBag.Eventos = eventos;


            return View("Views/Evento/Actualizar.cshtml");
        }



        [HttpPost]
        public IActionResult ActualizarEvento(EventoModel eventoModel)
        {

            var files = HttpContext.Request.Form.Files;

            String ruta = "";

            if (files.Count == 0)
            {
                ruta="0";
            }
            else {

                String p = Path.GetFullPath("wwwroot") + "/";
                var uploads = Path.Combine(p, @"Img");
                var extension = Path.GetExtension(files[0].FileName);

                if (System.IO.File.Exists(Path.Combine(uploads, eventoModel.Nombre + eventoModel.Recinto + eventoModel.NombreArtista + extension)))
                {

                    System.IO.File.Delete(Path.Combine(uploads, eventoModel.Nombre + eventoModel.Recinto + eventoModel.NombreArtista + extension));
                    using (var fileStream = new FileStream(Path.Combine(uploads, eventoModel.Nombre + eventoModel.Recinto + eventoModel.NombreArtista + extension), FileMode.Create))
                    {
                        files[0].CopyTo(fileStream);
                    }

                    ruta = eventoModel.Nombre + eventoModel.Recinto + eventoModel.NombreArtista + extension;
                }
                else {


                    using (var fileStream = new FileStream(Path.Combine(uploads, eventoModel.Nombre + eventoModel.Recinto + eventoModel.NombreArtista + extension), FileMode.Create))
                    {
                        files[0].CopyTo(fileStream);
                    }

                    ruta = eventoModel.Nombre + eventoModel.Recinto + eventoModel.NombreArtista + extension;


                }

                
            }

            String InicioA = eventoModel.Start_Event.ToString("yyyy-dd-MM HH:mm:ss");
            String FinA = eventoModel.End_Event.ToString("yyyy-dd-MM HH:mm:ss");

            if (ModelState.IsValid)
            {
                string connectionString = Configuration["ConnectionStrings:DB_Connection"];
                var connection = new SqlConnection(connectionString);

                string sqlQuery = $"exec sp_ActualizarEvento @param_Id='{eventoModel.Id}', @param_Nombre='{eventoModel.Nombre}', @param_Recinto='{eventoModel.Recinto}', @param_Time_Start_Event='{InicioA}',@param_Time_End_Event='{FinA}',@param_Taquilla={eventoModel.Taquilla}, @param_CancionFamosa='{eventoModel.CancionFamosa}', @param_NombreArtista='{eventoModel.NombreArtista}', @param_Imagen='{ruta}'";

                using (SqlCommand command = new SqlCommand(sqlQuery, connection))
                {
                    command.CommandType = System.Data.CommandType.Text;
                    connection.Open();
                    command.ExecuteReader();
                    connection.Close();
                }



            } // if

            return Administrar();
        }


        public IActionResult IndexEventos()
        {

            List<EventoModel> eventos = new List<EventoModel>();

            if (ModelState.IsValid)
            {
                string connectionString = Configuration["ConnectionStrings:DB_Connection"];
                using (SqlConnection connection = new SqlConnection(connectionString))
                {
                    string sqlQuery = $"exec sp_ListarEventos";
                    using (SqlCommand command = new SqlCommand(sqlQuery, connection))
                    {
                        command.CommandType = System.Data.CommandType.Text;
                        connection.Open();
                        SqlDataReader productosReader = command.ExecuteReader();
                        while (productosReader.Read())
                        {
                            eventos.Add(new EventoModel(
                                Int32.Parse(productosReader["ID_EVENT"].ToString()),
                                productosReader["NAME_EVENT"].ToString(),
                                productosReader["NAME_RECINTO"].ToString(),
                                DateTime.Parse(productosReader["TIME_START_EVENT"].ToString()),
                                DateTime.Parse(productosReader["TIME_END_EVENT"].ToString()),
                                Int32.Parse(productosReader["TAQUILLA"].ToString()),
                                Int32.Parse(productosReader["ID_ARTIST"].ToString()),
                                productosReader["NAME_ARTIST"].ToString(),
                                productosReader["FAMOUS_SONG"].ToString(),
                                productosReader["PHOTO_ARTIST"].ToString(),
                                productosReader["FINISHED"].ToString()
                                )
                            );
                        } // while
                        connection.Close();
                    } // using
                } // using
            } // if

            ViewBag.Eventos = eventos;

            return View("Views/Evento/IndexEventos.cshtml");
        }





    }
}
