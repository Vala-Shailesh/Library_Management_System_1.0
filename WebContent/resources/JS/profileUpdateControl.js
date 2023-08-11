console.log("script are starting......");

const updateForm = document.forms[0];
const formField = document.getElementsByClassName("field");
const enableBtn = document.querySelector(".enable");

console.log(updateForm);
console.log(formField);

// disabled freld
for (const item of formField) {
	item.disabled = true;
}

//  enable field 
enableBtn.addEventListener("click", () => {
	for (const item of formField) {
		item.disabled = false;
	}
});

console.log("script are starting......");