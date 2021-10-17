var currPos = 0;
var obj;

function showAnswer() {
  var getButton = document.getElementById("answerStatus");
  var status = getButton.innerText;

  if (status === "Show Answer") {
    getButton.innerText = "Hide Answer";
    for (let i=0; i<obj.answerCols.length; i++) {
      var idName = 'a' + i.toString();
      var question = obj.answerCols[i];
      var inputSpace = document.getElementById(idName);
      var answerSize = obj.questions[currPos].answers[question].length;
      var answer = '';
      if (answerSize === 1) {
        answer = obj.questions[currPos].answers[question][0];
      }

      else {
        for (let j=0; j<answerSize; j++) {
          answer += obj.questions[currPos].answers[question][j];
          if (j !== answerSize-1)
            answer += ", ";
        }
      }

      if (answer !== "-1")
        inputSpace.setAttribute("placeholder", answer);
    }
  }

  else {
    getButton.innerText = "Show Answer";
    for (let i=0; i<obj.answerCols.length; i++) {
      idName = 'a' + i.toString();
      question = obj.answerCols[i];
      inputSpace = document.getElementById(idName);
      answerSize = obj.questions[currPos].answers[question].length;
      answer = obj.questions[currPos].answers[question][0];
      if (answer !== "-1")
        inputSpace.setAttribute("placeholder", '');
    }
  }
}

async function showQuestion(questionPos) {

  // ./json/HTN_Dosage_List (3).json
  // /data/htn-dosage-list
  // ./json/HTN_alternative.json
   await fetch("./json/HTN_Dosage_List (3).json")
      .then(res => res.json())
      .then(data => obj = data)
  //.then(() => console.log(obj))  // don't delete this line

  // Make sure the currPos within the range
  if (questionPos < 0)
    currPos = 0;
  else if (questionPos > obj.rows-1)
    currPos = obj.rows - 1;
  else
    currPos = questionPos;

  var getButton = document.getElementById("answerStatus");
  if (getButton.innerText === "Hide Answer")
    getButton.innerText = "Show Answer";


  // Remove the previous information, be ready to load new question
  var div = document.getElementById("questionList");
  div.innerHTML = '';

  // Start loading new question data
  document.title = obj.title;
  document.getElementById("title").innerHTML = obj.title;

  let node = document.createElement("h4");
  node.innerHTML = obj.questionAsk + ": " + obj.questions[currPos].questionText;
  document.getElementById("questionList").appendChild(node);

  for (let i=0; i<obj.answerCols.length; i++) {

    node = document.createElement("h4");

    var question = document.createElement("text");
    question.setAttribute("id", currPos.toString());
    question.innerHTML = obj.answerCols[i] + ": "
    node.appendChild(question);
    document.getElementById("questionList").appendChild(node);

    var q = obj.answerCols[i];
    var a = obj.questions[currPos].answers[q][0];

    if (a !== "-1") {
      let inputSpace = document.createElement("input");
      inputSpace.setAttribute("id", "a" + i.toString())
      node.appendChild(inputSpace);
      document.getElementById("questionList").appendChild(node);
    }
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