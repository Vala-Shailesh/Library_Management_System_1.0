console.log("Script are starting.........");

const addBookBtn = document.getElementById("addBookBtn");
const updateBookBtn = document.getElementById("updateBookBtn");
const removeBookBtn = document.getElementById("removeBookBtn");

// footer part
const footer = document.querySelector(".footer");

console.log(footer);

const addBookForm = document.querySelector("#addBook");
const updateBookForm = document.querySelector("#updateBook");
const removeBookForm = document.querySelector("#removeBook");

console.log(addBookBtn, updateBookBtn, removeBookBtn);
console.log(addBookForm, updateBookForm, removeBookForm);

// display Add book form
addBookBtn.addEventListener("click", () => {
	addBookForm.classList.toggle("display-none");
	updateBookForm.classList.add("display-none");
	removeBookForm.classList.add("display-none");
	footer.classList.remove("footer");
});

// display Update book form
updateBookBtn.addEventListener("click", () => {
	updateBookForm.classList.toggle("display-none");
	addBookForm.classList.add("display-none");
	removeBookForm.classList.add("display-none");
	footer.classList.remove("footer");
});

// display Remove book form
removeBookBtn.addEventListener("click", () => {
	removeBookForm.classList.toggle("display-none");
	addBookForm.classList.add("display-none");
	updateBookForm.classList.add("display-none");
	footer.classList.remove("footer");
});


console.log("Script are ending.........");