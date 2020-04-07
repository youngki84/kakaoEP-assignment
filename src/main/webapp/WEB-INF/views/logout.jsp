<!DOCTYPE html>
<html>
  <head>
  <script>
  function fn_close_window(){
//       window.self.close();
      window.open("about:blank", "_self").close();
  }
  </script>
  </head>
  <body onload = fn_close_window()>
  </body>
</html>