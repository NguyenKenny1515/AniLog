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

function submitUpdateForm() {
    const form = document.getElementById("entry-update-form");
    const formData = new FormData(form);

    const entryData = {};
    formData.forEach((value, key) => {
        entryData[key] = value;
    });

    fetch(form.getAttribute("data-action"), {
        method: "PATCH",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(entryData)
    })
    .then(response => {
        if (response.ok) {
            location.reload();
        } else {
            console.log("Error updating entry");
        }
    })
    .catch(error => {
        console.error("Error: ", error);
    });
}