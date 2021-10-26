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
