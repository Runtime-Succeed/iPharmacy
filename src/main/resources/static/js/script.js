function fetchData() {
//jsontest.html
  fetch("./json/htndosagelist.json")
      .then(function (resp) {
        return resp.json();
  })
      .then(function(data) {
        console.log(data);
      });
}

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

var currPos = 0;

const obj = JSON.parse('{\n' +
    '  "title" : "HTN Dosage List",\n' +
    '  "rows" : 30,\n' +
    '  "columns" : [ "Generic", "Brand", "Dose (mg)", "Max Dose (mg)" ],\n' +
    '  "questions" : [ {\n' +
    '    "qText" : "Chlorthalidone",\n' +
    '    "answers" : {\n' +
    '      "Brand" : [ "Thalitone" ],\n' +
    '      "Dose (mg)" : [ "25 QD" ],\n' +
    '      "Max Dose (mg)" : [ "100 QD" ]\n' +
    '    }\n' +
    '  }, {\n' +
    '    "qText" : "Lisinopril",\n' +
    '    "answers" : {\n' +
    '      "Brand" : [ "Prinivil", "Zestril", "Qbrelis" ],\n' +
    '      "Dose (mg)" : [ "10-40 QD" ],\n' +
    '      "Max Dose (mg)" : [ "40 QD" ]\n' +
    '    }\n' +
    '  }, {\n' +
    '    "qText" : "Hydralazine",\n' +
    '    "answers" : {\n' +
    '      "Brand" : [ "-1" ],\n' +
    '      "Dose (mg)" : [ "10-50 TID-QID" ],\n' +
    '      "Max Dose (mg)" : [ "200 QD" ]\n' +
    '    }\n' +
    '  } ]\n' +
    '}');