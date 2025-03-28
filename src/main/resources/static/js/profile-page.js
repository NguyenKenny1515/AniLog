const imageInput = document.getElementById("image-input");
const uploadButton = document.getElementById("upload-button");
const profilePicForm = document.getElementById("profile-pic-form");
const changePictureIcon = document.querySelector(".change-picture-icon");

imageInput.addEventListener("change", () => {
    uploadButton.disabled = imageInput.files.length <= 0;
});

changePictureIcon.addEventListener("click", () => {
    profilePicForm.classList.remove("d-none");
});

let sortOrder = {};

function sortGrid(columnIndex) {
    const table = document.querySelector("table tbody");
    const rows = Array.from(table.rows);

    if (!sortOrder[columnIndex]) {
        sortOrder[columnIndex] = 1;
    } else {
        sortOrder[columnIndex] *= -1;
    }

    rows.sort((rowA, rowB) => {
        let cellA = rowA.cells[columnIndex].innerText.trim();
        let cellB = rowB.cells[columnIndex].innerText.trim();

        if (!isNaN(cellA) && !isNaN(cellB)) {
            cellA = Number(cellA);
            cellB = Number(cellB);
        }

        return (cellA > cellB ? 1 : -1) * sortOrder[columnIndex];
    });

    rows.forEach((row, index) => {
        row.cells[0].innerText = index + 1;
        table.appendChild(row);
    });
}

document.addEventListener("DOMContentLoaded", function() {
    // Initially sort by title
     sortGrid(1);
     const changePictureIcon = document.querySelector(".change-picture-icon");
     if (changePictureIcon) {
         changePictureIcon.style.cursor = "pointer";
     }
});

function enforceLimits(className, min, max) {
    let inputs = document.querySelectorAll(`.${className}`);

    inputs.forEach(input => {
        input.addEventListener("blur", function () {
            let value = parseFloat(this.value);

            if (value > max) {
                this.value = max;
            } else if (value < min || isNaN(value)) {
                this.value = min;
            }
        });
    });
}

enforceLimits("episodes-watched", 0, parseInt(document.querySelector(".episodes-watched").getAttribute("max")) || 0);
enforceLimits("user-score", 0, 10);

document.querySelectorAll('.delete-form').forEach(form => {
    form.addEventListener('submit', function(event) {
        if (!confirm("Are you sure you want to delete this entry?")) {
            // Prevent form submission if user cancels
            event.preventDefault();
        }
    });
});