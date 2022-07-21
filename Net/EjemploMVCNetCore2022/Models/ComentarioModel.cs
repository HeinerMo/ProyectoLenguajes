using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace EjemploMVCNetCore2022.Models
{
    public class ComentarioModel
    {

        public ComentarioModel()
        {
        }


        public ComentarioModel(string estudiante, string artista, string comentario)
        {
            Estudiante = estudiante;
            Artista = artista;
            Comentario = comentario;
        }

        public string Estudiante { get; set; }

        public string Artista { get; set; }

        public string Comentario { get; set; }
    }
}
