#pragma checksum "C:\Users\sojos\Desktop\Lenguajes\ProyectoFinal\EjemploMVCNetCore2022\EjemploMVCNetCore2022\Views\Session\Index.cshtml" "{ff1816ec-aa5e-4d10-87f7-6f4963833460}" "7efe6893c795bafbb6ab9165d038d685106ef62a"
// <auto-generated/>
#pragma warning disable 1591
[assembly: global::Microsoft.AspNetCore.Razor.Hosting.RazorCompiledItemAttribute(typeof(AspNetCore.Views_Session_Index), @"mvc.1.0.view", @"/Views/Session/Index.cshtml")]
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
#line 1 "C:\Users\sojos\Desktop\Lenguajes\ProyectoFinal\EjemploMVCNetCore2022\EjemploMVCNetCore2022\Views\_ViewImports.cshtml"
using EjemploMVCNetCore2022;

#line default
#line hidden
#nullable disable
#nullable restore
#line 2 "C:\Users\sojos\Desktop\Lenguajes\ProyectoFinal\EjemploMVCNetCore2022\EjemploMVCNetCore2022\Views\_ViewImports.cshtml"
using EjemploMVCNetCore2022.Models;

#line default
#line hidden
#nullable disable
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"7efe6893c795bafbb6ab9165d038d685106ef62a", @"/Views/Session/Index.cshtml")]
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"17a5ba720e8f8c8fc44f92fcb215d818672ead0a", @"/Views/_ViewImports.cshtml")]
    public class Views_Session_Index : global::Microsoft.AspNetCore.Mvc.Razor.RazorPage<SessionModel>
    {
        #pragma warning disable 1998
        public async override global::System.Threading.Tasks.Task ExecuteAsync()
        {
            WriteLiteral("\r\n\r\n");
#nullable restore
#line 4 "C:\Users\sojos\Desktop\Lenguajes\ProyectoFinal\EjemploMVCNetCore2022\EjemploMVCNetCore2022\Views\Session\Index.cshtml"
  
    ViewData["Title"] = "Index ejemplo Session";

#line default
#line hidden
#nullable disable
            WriteLiteral("\r\n<h2>Index Session</h2>\r\n<h3>SessionModel: ");
#nullable restore
#line 9 "C:\Users\sojos\Desktop\Lenguajes\ProyectoFinal\EjemploMVCNetCore2022\EjemploMVCNetCore2022\Views\Session\Index.cshtml"
             Write(Model.VariableSession);

#line default
#line hidden
#nullable disable
            WriteLiteral("</h3>\r\n");
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
        public global::Microsoft.AspNetCore.Mvc.Rendering.IHtmlHelper<SessionModel> Html { get; private set; }
    }
}
#pragma warning restore 1591