using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace EjemploMVCNetCore2022.Models
{
    public class EventoModel
    {

        public EventoModel() { 
        }

        public EventoModel(int id,string nombre,string recinto, DateTime start,DateTime end, int taquilla, int idArtista, string nombreArtista, string cancionFamosa, string imagen)
        {
            Id = id;
            Nombre = nombre;
            Recinto = recinto;
            Start_Event = start;
            End_Event = end;
            Taquilla = taquilla;
            IdArtista = idArtista;
            NombreArtista = nombreArtista;
            CancionFamosa = cancionFamosa;
            Imagen = imagen;
        }

        public EventoModel(int id, string nombre, string recinto, DateTime start, DateTime end, int taquilla, int idArtista, string nombreArtista, string cancionFamosa, string imagen, string estado)
        {
            Id = id;
            Nombre = nombre;
            Recinto = recinto;
            Start_Event = start;
            End_Event = end;
            Taquilla = taquilla;
            IdArtista = idArtista;
            NombreArtista = nombreArtista;
            CancionFamosa = cancionFamosa;
            Imagen = imagen;
            Estado = estado;
        }

        public int Id { get; set; }

        public string Nombre { get; set; }

        public string Recinto { get; set; }

        public DateTime Start_Event { get; set; }

        public DateTime End_Event { get; set; }

        public int Taquilla { get; set; }

        public int IdArtista { get; set; }

        public string NombreArtista { get; set; }

        public string CancionFamosa { get; set; }

        public string Imagen { get; set; }

        public string Estado { get; set; }

        public override string ToString()
        {
            return "Nombre: "+Nombre+" Recinto: " + Nombre + " Start: " + Start_Event + " End: " + End_Event + " Taquilla: " + Taquilla;
        }

    } // fin clase 
} // fin namespace
