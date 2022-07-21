using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using EjemploMVCNetCore2022.Models;
using Microsoft.Extensions.Configuration;
using System.Data.SqlClient;

namespace EjemploMVCNetCore2022.Controllers
{
    public class LoginController : Controller
    {

        public IConfiguration Configuration { get; }

        public LoginController(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IActionResult Login()
        {
            return View();
        }

        [HttpPost]
        public IActionResult IndexLogin(LoginModel loginModel)
        {

            String  admin = "";
            if (ModelState.IsValid)
            {
                string connectionString = Configuration["ConnectionStrings:DB_Connection"];
                using (SqlConnection connection = new SqlConnection(connectionString))
                {
                    string sqlQuery = $"exec sp_Login @param_Name='{loginModel.Usuario}',@param_Password='{loginModel.Contrasenia}'";

                    using (SqlCommand command = new SqlCommand(sqlQuery, connection))
                    {
                        command.CommandType = System.Data.CommandType.Text;
                        connection.Open();
                        SqlDataReader productosReader = command.ExecuteReader();
                        while (productosReader.Read())
                        {
                            admin = productosReader["LOGIN"].ToString();
                        } // while
                        connection.Close();
                    } // using
                } // using
            } // if

            if (admin.Equals("1"))
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
            else if (admin.Equals("0"))
            {
                return View("Views/Login/Login.cshtml");
            }

            return View("Views/Login/Login.cshtml");
        }
    }
}
