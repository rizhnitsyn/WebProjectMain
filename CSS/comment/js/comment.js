// JavaScript Document
function doLoad(value){
    err=document.getElementById('cerror');
	err.innerHTML = "<img src='images/upload.gif' width='32' height='8'><br><p>Ваш комментарий обрабатывается...</p>";
	// Create new JsHttpRequest object.
    var req = new JsHttpRequest();
    // Code automatically called on load finishing.
    req.onreadystatechange = function() {
        if (req.readyState == 4) {
		  if (req.responseJS.error == 'no') {
            // Clear error information.
            err.innerHTML = "";
			// Write req.responseJS to page element (_req.responseJS become responseJS). 
            err.innerHTML = req.responseJS.ok;
			
			
			form = document.getElementById('addcom');
			form.text.value = "";
			form.author.value = "";	
			
          }
		  else
		  {
		  err.innerHTML = req.responseJS.er_mess;
		  }  	
        }
    }
    // Prepare request object (automatically choose GET or POST).
    req.open(null, 'comment.php', true);
    // Send data to backend.
    req.send( { q: value } );
}

