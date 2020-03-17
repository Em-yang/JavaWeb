# MVC

是一种开发模式，将程序分层的一种思想

M：Model			业务逻辑 （service、repository、entity）

V：View				视图 （JSP、HTML、APP客户端）

C：Controller	   控制（Servlet、Handler、Action）

请求进入Java Web应用后，Controller接受该请求后，进行业务逻辑处理，最终将处理的结果在返回给用户（View + Model）

Controller -->  Service --> Repository --> DB

请求进入 Controller，进行业务处理，从 Controller 中将 Model 带到 View 中响应给用户。