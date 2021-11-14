function handleSubmit() {
    var input = document.getElementById('input').value;
    var password = document.getElementById('password').value;
    let  data = {
            "username": input,
            "password": password
    }

    fetch('/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    })
    .then(response => response.json())
//    .then(data => console.log(data));
    .then(data => convert(data));

    window.onload = function() {
        const button = document.getElementById('bu');
        button.addEventListener('click', handleSubmit);
    }
}

function convert(data) {
    var showMessage = document.getElementById('showMessage');
    if(data["loginSuccess"] == false) {
        document.getElementById('input').setAttribute("class", "form-control is-invalid");
        document.getElementById('password').setAttribute("class", "form-control is-invalid");
        showMessage.innerHTML = "Username and Password doesn't match!";
    }
    else if (data["loginSuccess"] == true) {
        window.location.href='category.html';
    }
}

