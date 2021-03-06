 function checkPassword() {
     var pass = document.getElementById('pswd');
     var confirm = document.getElementById('confirm');
     const button = document.querySelector('button');

     if(confirm.value != pass.value){
         confirm.setAttribute("class", "form-control is-invalid");
         button.disabled = true;
     }
     else{
         confirm.setAttribute("class", "form-control is-valid");
         button.disabled = false;
     }
 }

 function allValid() {
     var first = document.getElementById('firstName');
     var last = document.getElementById('lastName');
     var user = document.getElementById('username');
     var email = document.getElementById('email');
     var pass = document.getElementById('pswd');
     var conf = document.getElementById('confirm');

     if (first.checkValidity() && last.checkValidity() && user.checkValidity() && email.checkValidity()
     && pass.checkValidity() && conf.checkValidity()) {
         return true;
     }
     else
         return false;

 }

 function handleSubmit() {
     if(allValid()) {
         data = {
              "firstName": document.getElementById('firstName').value,
              "lastName": document.getElementById('lastName').value,
              "username": document.getElementById('username').value,
              "email": document.getElementById('email').value,
              "password": document.getElementById('pswd').value
         }
         let response = fetch('/signup', {
             method: 'POST',
             headers: {
                 'Content-Type': 'application/json',
             },
             body:JSON.stringify(data),
         })
         .then(response => response.json())
         .then(data => convert(data));
         window.onload = function() {
             const button = document.getElementByTagName('button');
             button.addEventListener('click', handleSubmit);
         }
     }
 }

 function convert(data) {
    var username = document.getElementById('username');
    if(data["signupSuccess"] == false){
        username.value = '';
        username.setAttribute("class", "form-control is-invalid");
        document.getElementById("usernameInvalid").innerHTML = "This username already exists.";
    }
    else if (data["signupSuccess"] == true){
        username.setAttribute("class", "form-control is-valid");
        document.getElementById('myModal').style.display = 'block';
    }
 }
