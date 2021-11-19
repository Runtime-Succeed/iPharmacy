function handleSubmit() {
    var input = document.getElementById('input').value;
    var password = document.getElementById('password').value;
    let data = ""
    if (input.indexOf('@') >= 0) {
        data = {
            "email": input,
            "password": password
        }
    }
    else {
        data = {
            "username": input,
            "password": password
        }
    }
    
    fetch('/login?username=user1&password=password1', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    })
    .then(response => {
    response.json();
        window.location.href = 'category.html';
        }
    
    )
    .then(data => console.log(data));
    
 
    window.onload = function() {
        const button = document.getElementById('bu');
        button.addEventListener('click', handleSubmit);
    }
}

