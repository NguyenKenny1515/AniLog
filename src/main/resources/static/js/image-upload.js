const imageInput = document.getElementById("image-input");
const uploadButton = document.getElementById("upload-button");

imageInput.addEventListener("change", () => {
    uploadButton.disabled = imageInput.files.length <= 0;
});
