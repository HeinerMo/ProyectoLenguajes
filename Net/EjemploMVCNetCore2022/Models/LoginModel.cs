using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace EjemploMVCNetCore2022.Models
{
    public class LoginModel
    {

        public LoginModel()
        {
        }

        public LoginModel(string usuario, string contrasenia)
        {
            Usuario = usuario;
            Contrasenia = contrasenia;

        }

        public String Usuario { get; set; }

        public String Contrasenia { get; set; }

    }//
}//
