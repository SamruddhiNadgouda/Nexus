function previewImage(event) {
    const file = event.target.files[0];
    const preview = document.getElementById('upload_image_preview');

    if (file) {
        const reader = new FileReader();
        reader.onload = function(e) {
            preview.src = e.target.result;
        }
        reader.readAsDataURL(file);
    } else {
        preview.src = "";
    }
}