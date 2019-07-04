//document.addEventListener('load', status());

function status(val) {
    console.log("this is val", val);
  console.log("hello world!! I'm back");
  el = document.querySelector(".testing");
  //el = document.querySelectorAll(".testing");
  element = document.querySelector("#checkbox");
  console.log("this is value of checkbox", element.checked);
  if (val) {
    el.classList.remove("test");
  } else {
    el.classList.add("test");
  }
}
