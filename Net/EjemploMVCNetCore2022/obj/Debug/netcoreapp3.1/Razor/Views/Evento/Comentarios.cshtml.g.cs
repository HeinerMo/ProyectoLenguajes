#pragma checksum "C:\Users\sojos\Desktop\Lenguajes\ProyectoFinal\IF4101_ProyectoFinal_B87581_B85042\EjemploMVCNetCore2022\Views\Evento\Comentarios.cshtml" "{ff1816ec-aa5e-4d10-87f7-6f4963833460}" "e515c2c116c247e62567f44f09e2fce3b27b15d5"
// <auto-generated/>
#pragma warning disable 1591
[assembly: global::Microsoft.AspNetCore.Razor.Hosting.RazorCompiledItemAttribute(typeof(AspNetCore.Views_Evento_Comentarios), @"mvc.1.0.view", @"/Views/Evento/Comentarios.cshtml")]
namespace AspNetCore
{
    #line hidden
    using System;
    using System.Collections.Generic;
    using System.Linq;
    using System.Threading.Tasks;
    using Microsoft.AspNetCore.Mvc;
    using Microsoft.AspNetCore.Mvc.Rendering;
    using Microsoft.AspNetCore.Mvc.ViewFeatures;
#nullable restore
#line 1 "C:\Users\sojos\Desktop\Lenguajes\ProyectoFinal\IF4101_ProyectoFinal_B87581_B85042\EjemploMVCNetCore2022\Views\_ViewImports.cshtml"
using EjemploMVCNetCore2022;

#line default
#line hidden
#nullable disable
#nullable restore
#line 2 "C:\Users\sojos\Desktop\Lenguajes\ProyectoFinal\IF4101_ProyectoFinal_B87581_B85042\EjemploMVCNetCore2022\Views\_ViewImports.cshtml"
using EjemploMVCNetCore2022.Models;

#line default
#line hidden
#nullable disable
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"e515c2c116c247e62567f44f09e2fce3b27b15d5", @"/Views/Evento/Comentarios.cshtml")]
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"17a5ba720e8f8c8fc44f92fcb215d818672ead0a", @"/Views/_ViewImports.cshtml")]
    public class Views_Evento_Comentarios : global::Microsoft.AspNetCore.Mvc.Razor.RazorPage<ComentarioModel>
    {
        #pragma warning disable 1998
        public async override global::System.Threading.Tasks.Task ExecuteAsync()
        {
            WriteLiteral("\r\n");
#nullable restore
#line 2 "C:\Users\sojos\Desktop\Lenguajes\ProyectoFinal\IF4101_ProyectoFinal_B87581_B85042\EjemploMVCNetCore2022\Views\Evento\Comentarios.cshtml"
  
    ViewData["Title"] = "Comentarios";
    

#line default
#line hidden
#nullable disable
            WriteLiteral(@"<h1 class=""jumbotron-heading""></h1>
<section class=""jumbotron text-center"">
    <div class=""container"">
        <h1 class=""jumbotron-heading"">Comentarios</h1>
        <table class=""table"">

            <thead>
                <tr>

                    <th>
                        Estudiante
                    </th>
                    <th>
                        Artista
                    </th>
                    <th>
                        Comentario
                    </th>


                    <th></th>
                </tr>
            </thead>
            <tbody>

");
#nullable restore
#line 31 "C:\Users\sojos\Desktop\Lenguajes\ProyectoFinal\IF4101_ProyectoFinal_B87581_B85042\EjemploMVCNetCore2022\Views\Evento\Comentarios.cshtml"
                 foreach (ComentarioModel eventoActual in ViewBag.Comentarios)
                {


#line default
#line hidden
#nullable disable
            WriteLiteral("                    <tr>\r\n                        <td>\r\n                            ");
#nullable restore
#line 36 "C:\Users\sojos\Desktop\Lenguajes\ProyectoFinal\IF4101_ProyectoFinal_B87581_B85042\EjemploMVCNetCore2022\Views\Evento\Comentarios.cshtml"
                       Write(Html.DisplayFor(modelItem => eventoActual.Estudiante));

#line default
#line hidden
#nullable disable
            WriteLiteral("\r\n                        </td>\r\n                        <td>\r\n                            ");
#nullable restore
#line 39 "C:\Users\sojos\Desktop\Lenguajes\ProyectoFinal\IF4101_ProyectoFinal_B87581_B85042\EjemploMVCNetCore2022\Views\Evento\Comentarios.cshtml"
                       Write(Html.DisplayFor(modelItem => eventoActual.Artista));

#line default
#line hidden
#nullable disable
            WriteLiteral("\r\n                        </td>\r\n                        <td>\r\n                            ");
#nullable restore
#line 42 "C:\Users\sojos\Desktop\Lenguajes\ProyectoFinal\IF4101_ProyectoFinal_B87581_B85042\EjemploMVCNetCore2022\Views\Evento\Comentarios.cshtml"
                       Write(Html.DisplayFor(modelItem => eventoActual.Comentario));

#line default
#line hidden
#nullable disable
            WriteLiteral("\r\n                        </td>\r\n                    </tr>\r\n");
#nullable restore
#line 45 "C:\Users\sojos\Desktop\Lenguajes\ProyectoFinal\IF4101_ProyectoFinal_B87581_B85042\EjemploMVCNetCore2022\Views\Evento\Comentarios.cshtml"

                }

#line default
#line hidden
#nullable disable
            WriteLiteral("\r\n            </tbody>\r\n\r\n        </table>\r\n    </div>\r\n</section>\r\n");
        }
        #pragma warning restore 1998
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.ViewFeatures.IModelExpressionProvider ModelExpressionProvider { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.IUrlHelper Url { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.IViewComponentHelper Component { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.Rendering.IJsonHelper Json { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.Rendering.IHtmlHelper<ComentarioModel> Html { get; private set; }
    }
}
#pragma warning restore 1591
