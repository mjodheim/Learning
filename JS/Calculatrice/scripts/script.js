/* ----- RECUPERATION DES ELEMENTS ----- */
let display = document.getElementById("display");

let numbers = document.querySelectorAll(".btn-number");
let operators = document.querySelectorAll(".btn-operator");

let equal = document.getElementById("btn-equal");
let clear = document.getElementById("btn-clear");
let point = document.getElementById("btn-point");

/* ----- INITIALISATION DES VARIABLES GLOBALES ----- */
let lastDigit = display.textContent.substring(display.textContent.length - 1, display.textContent.length);
let result = 0;
let lastNumber = '';
let lastOperator = '';
let counter = 0;
let resetDisplay = false;

/* ----- GESTION DES EVENTS ----- */

// Event sur click number
numbers.forEach(number => {
    number.addEventListener("click", function() {
        gestionNumbers(number.textContent);
    })
})

// Event sur click operator
operators.forEach(operator => {
    operator.addEventListener("click", function() {
        gestionOperators(operator.textContent);
    })
})

// Event sur click point
point.addEventListener("click", function() {
    gestionPoint();
})

// Event sur click clear
clear.addEventListener("click", function() {
    reinit();
    display.textContent = "0";
})

// Event sur click equal
equal.addEventListener("click", function() {
    if (parseFloat(lastNumber) === 0 && lastOperator === "/") {
        display.textContent = "Division pas zéro interdite !";
        resetDisplay = true;
        return;
    }
    testOperator(lastOperator);
    display.textContent = result.toString();
    reinit();
})

// Event sur key
document.addEventListener("keydown", function(key) {
    if (('1234567890').includes(key.key)) {
        gestionNumbers(key.key.toString());
    }
    if (('+-*/').includes(key.key)) {
        gestionOperators(key.key.toString());
    }
    if (key.key === "Enter"){
        if (parseFloat(lastNumber) === 0 && lastOperator === "/") {
            display.textContent = "Division pas zéro interdite !";
            resetDisplay = true;
            return;
        }
        testOperator(lastOperator);
        display.textContent = result.toString();
        reinit();
    }
    if(key.key === "."){
        gestionPoint();
    }
    if(key.key === "Delete"){
        reinit();
        display.textContent = "0";
    }
})

/* ----- GESTION DU DARK MODE ----- */
let displayMode = document.getElementById("display-mode");
displayMode.addEventListener("click", function() {
    if(displayMode.innerText === "Basculer en mode sombre"){
        document.getElementsByTagName('body')[0].style.backgroundColor = '#142d3a';
        document.getElementsByTagName('footer')[0].style.color = 'Aliceblue';
        document.getElementsByTagName('a')[0].style.color = 'Aliceblue';
        operators.forEach(operator => {
            operator.style.backgroundColor = 'darkorange';
        })
        displayMode.innerText = 'Basculer en mode clair';
    }
    else{
        document.getElementsByTagName('body')[0].style.backgroundColor = 'lightblue';
        displayMode.innerText = 'Basculer en mode sombre';
        document.getElementsByTagName('footer')[0].style.color = 'black';
        operators.forEach(operator => {
            operator.style.backgroundColor = 'gold';
        })
        document.getElementsByTagName('a')[0].style.color = '#142d3a';
    }
})

/* ----- FONCTIONS ----- */

function testOperator(operator) {
    switch(operator) {
        case "-":
            result -= parseFloat(lastNumber);
            break;
        case "+":
            result += parseFloat(lastNumber);
            break;
        case "*":
            result *= parseFloat(lastNumber);
            break;
        case "/":
            result /= parseFloat(lastNumber);
            break;
        default:
            display.textContent = 'ERROR';
            break;
    }
}

function gestionOperators(operator){
    if(resetDisplay){
        return;
    }
    if("+-*/.".includes(lastDigit)) {
        return;
    }
    if(counter === 0){
        result = parseFloat(display.textContent);
    }
    else{
        testOperator(lastOperator);
    }
    lastOperator = operator;
    display.textContent += operator;
    lastDigit = display.textContent.substring(display.textContent.length - 1, display.textContent.length);
    lastNumber = '';
    counter ++;
}

function gestionNumbers(number) {
    if(resetDisplay){
        display.textContent = '';
        reinit();
        resetDisplay = false;
    }
    if(display.textContent === '0'){
        display.textContent = number;
        lastNumber += number;
    }
    else{
        display.textContent += number;
        lastDigit = display.textContent.substring(display.textContent.length - 1, display.textContent.length);
        lastNumber += number;
    }
}

function gestionPoint(){
    if(resetDisplay){
        return;
    }
    if(".+-*/".includes(lastDigit) || lastNumber.includes('.')) {
        return;
    }
    display.textContent += point.textContent;
    lastDigit = display.textContent.substring(display.textContent.length - 1, display.textContent.length);
    lastNumber += '.';
}

function reinit(){
    lastDigit = display.textContent.substring(display.textContent.length - 1, display.textContent.length);
    result = 0;
    lastNumber = '';
    lastOperator = '';
    counter = 0;
}