var currPos = 0;
var obj;

async function showQuestion(questionPos) {
  // await fetch("./json/HTN_Dosage_List (3).json")
  await fetch("/data/htn-dosage-list")
      .then(res => res.json())
      .then(data => obj = data)
  //.then(() => console.log(obj))  // don't delete this line

  currPos = questionPos;
  document.title = obj.title;
  document.getElementById("title").innerHTML = obj.title;

  let node = document.createElement("h4");
  node.innerHTML = obj.questionAsk + ": " + obj.questions[questionPos].questionText;
  document.getElementById("questionList").appendChild(node);

  for (let i=0; i<obj.answerCols.length; i++) {

    node = document.createElement("h4");

    var question = document.createElement("text");
    question.setAttribute("id", questionPos.toString());
    question.innerHTML = obj.answerCols[i] + ": "
    node.appendChild(question);
    document.getElementById("questionList").appendChild(node);

    let inputSpace = document.createElement("input");
    inputSpace.setAttribute("id", "a" + i.toString())
    node.appendChild(inputSpace);
    document.getElementById("questionList").appendChild(node);
  }
}

function checkAnswer() {
  let result = true;
  let answerList = []

  for (let i=0; i<obj.columns.length; i++) {

    answerList.push(document.getElementById("a" + i.toString()).value);

    let temp = obj.columns[i];
    // if the answer has single value only
    if (obj.questions[currPos].answers[temp].length === 1) {
      let answerCheck = obj.questions[currPos].answers[temp][0];
      if (answerList[i-1] !== answerCheck)
        result = false;
    }

    // if we have more than 1 answer
    // TODO
  }

  if (result) {
    let node = document.createElement("h5");
    node.innerHTML = "All correct!";
    document.getElementById("result").appendChild(node);
  }
  else {
    let node = document.createElement("h5");
    node.innerHTML = "Something is wrong";
    document.getElementById("result").appendChild(node);
  }
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

// let obj2 = JSON.parse('{\n' +
//     '  "title" : "HTN Dosage List",\n' +
//     '  "rows" : 30,\n' +
//     '  "columns" : [ "Generic", "Brand", "Dose (mg)", "Max Dose (mg)" ],\n' +
//     '  "questions" : [ {\n' +
//     '    "qText" : "Chlorthalidone",\n' +
//     '    "answers" : {\n' +
//     '      "Brand" : [ "Thalitone" ],\n' +
//     '      "Dose (mg)" : [ "25 QD" ],\n' +
//     '      "Max Dose (mg)" : [ "100 QD" ]\n' +
//     '    }\n' +
//     '  }, {\n' +
//     '    "qText" : "Lisinopril",\n' +
//     '    "answers" : {\n' +
//     '      "Brand" : [ "Prinivil", "Zestril", "Qbrelis" ],\n' +
//     '      "Dose (mg)" : [ "10-40 QD" ],\n' +
//     '      "Max Dose (mg)" : [ "40 QD" ]\n' +
//     '    }\n' +
//     '  }, {\n' +
//     '    "qText" : "Hydralazine",\n' +
//     '    "answers" : {\n' +
//     '      "Brand" : [ "-1" ],\n' +
//     '      "Dose (mg)" : [ "10-50 TID-QID" ],\n' +
//     '      "Max Dose (mg)" : [ "200 QD" ]\n' +
//     '    }\n' +
//     '  } ]\n' +
//     '}');