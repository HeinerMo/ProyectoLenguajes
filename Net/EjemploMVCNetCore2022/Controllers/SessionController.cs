using EjemploMVCNetCore2022.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace EjemploMVCNetCore2022.Controllers
{
    public class SessionController : Controller
    {
        public IActionResult Index()
        {

            HttpContext.Session.SetString("nombreVariable","valor en session");
            HttpContext.Session.SetInt32("variableInt", 0);

            SessionModel modeloSession = new SessionModel();
            modeloSession.VariableSession= HttpContext.Session.GetString("nombreVariable");

            return View(modeloSession);
        }
    }
}
