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

window.addEventListener('DOMContentLoaded', (event) => {
    var button = document.getElementById("sub");
    button.onclick = async(e) => {
        data = {
             "firstname": document.getElementById('firstName').value,
             "lastname": document.getElementById('lastName').value,
             "username": document.getElementById('username').value,
             "email": document.getElementById('email').value,
             "password": document.getElementById('pswd').value
        }
        console.log(data);
        if(allValid()){
            let response = await fetch('localhost:8080/signup', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body:JSON.stringify(data),
            })
            .then(response => response.json())
            .then(data => console.log(data));
        }
    }
})


