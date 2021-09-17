function createAccount() {
  //console.log("create account");

  var username = $('#username').val();
  //console.log(username);

  var password = $('#password').val();
  //console.log(password);

  if (username != "" && password != "") {
    $.ajax({
    type : "POST",
    url : "/create-account/",
    data : {
      "username" : username,
      "password" : password
    },
    success : function(result) {
      populateTable();
    }
    });
  }
  
}

function getRandom() {
  //console.log("get random");  

  $.ajax({
    type : "GET",
    url : "/random-number",
    success : function(result) {
      $('#randomNumber').text(result);
    }
  });
}

function populateTable() {

  $.ajax({
    type : "GET",
    url : "/display-accounts",
    success : function(result) {
      
      $('#table').empty();
      $('#table').append('<tr><th>Username</th><th>Password</th></tr>');
      var accounts = JSON.parse(result);
      for(var i = 0; i < accounts.length; i++)
      {
        $('#table').append('<tr><td>' + accounts[i].username + '</td><td>' + accounts[i].password + '</td></tr>');
      }
    }
  });

}