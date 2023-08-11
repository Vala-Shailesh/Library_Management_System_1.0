console.log("Script are starting......");

const formCloseBtn = document.querySelector(".close-form-icon");
const form = document.querySelector(".login-box");
const formOpenBtn = document.querySelector(".open-login-form");

// popup event 
document.addEventListener("DOMContentLoaded", () => {

	// console.log(form.style.display == "none" );

	if (form.style.display == "block") {

		setTimeout(() => {
			document.body.style.pointerEvents = "none";
			document.body.style.opacity = "0.77";
			form.style.pointerEvents = "all";
			form.classList.remove("display-none");
		}, 5000);
	}
});

// open login form
// console.log(formOpenBtn);
formOpenBtn.addEventListener("click", () => {
	form.style.display = "block";
	document.body.style.pointerEvents = "none";
	document.body.style.opacity = "0.77";
	form.style.pointerEvents = "all";
});

// close login form
formCloseBtn.addEventListener("click", () => {
	form.style.display = "none";
	document.body.style.opacity = "1";
	document.body.style.pointerEvents = "all";
});

// bridge between login-registration
const login = document.querySelector(".login-open");
const registration = document.querySelector(".registration-open");

const loginForm = document.querySelector("#login");
const registrationForm = document.querySelector("#registration");

console.log(login, registration);

login.addEventListener("click", (e) => {
	e.preventDefault();
	loginForm.classList.toggle("display-none");
	registrationForm.classList.toggle("display-none");
});

registration.addEventListener("click", (e) => {
	e.preventDefault();
	loginForm.classList.toggle("display-none");
	registrationForm.classList.toggle("display-none");
});


console.log("Script are ending......");