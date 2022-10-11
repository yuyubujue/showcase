
//choose

function showImages() {
  const content = document.getElementById('images');
  content.innerHTML = '';
  document.getElementById('feedback').innerText = null;
  const images = document.querySelector('#images');
  const files = document.querySelector('#pj_images').files;
  function readAndPreview(file) {
    if (/\.(jpe?g|png|gif)$/i.test(file.name)) {
      const reader = new FileReader();
      reader.addEventListener("load", () => {
        const image = new Image();
        image.height = 100;
        image.title = file.name;
        image.src = reader.result;
        images.appendChild(image);
      }, false);

      reader.readAsDataURL(file);
    }
  }

  if (files) {
    Array.prototype.forEach.call(files, readAndPreview);
    if(files.length != 0){
      showbutton();
    }
    
  }
}

const showbutton = () => {
  document.getElementById("upimg").style.display = "block";
}
const hidebutton = () => {
  document.getElementById("upimg").style.display = "none";
}

function Uploadimg(){
    var files = document.querySelector('#pj_images').files;
    document.getElementById('feedback').innerText = `${files.length} file(s) uploaded successfully!`;
    hidebutton();
}

function previewFile() {
  var preview = document.querySelector('#pjCoverimg');
  var file    = document.querySelector('#pj_cover').files[0];
  var reader  = new FileReader();

  reader.addEventListener("load", function () {
    preview.src = reader.result;
  }, false);

  if (file) {
    reader.readAsDataURL(file);
  }
}



// drag  
// const dropZone = document.getElementById('pjimgupload');
// const content = document.getElementById('images');
// const reader = new FileReader();

// if (window.FileList && window.File) {
//   dropZone.addEventListener('dragover', event => {
//     event.stopPropagation();
//     event.preventDefault();
//     event.dataTransfer.dropEffect = 'copy';
//   });
  
//   dropZone.addEventListener('drop', event => {
//     content.innerHTML = '';
//     event.stopPropagation();
//     event.preventDefault();
//     const files = event.dataTransfer.files;
//     console.log(files);
    
//     reader.readAsDataURL(files[0]);
  
//     reader.addEventListener('load', (event) => {
//       content.innerHTML = '';
//       const img = document.createElement('img');
//       img.style.height = '400px';
//       img.style.width = '400px';
//       content.appendChild(img);
//       img.src = event.target.result;
//       img.alt = file.name;
      
//     });
//   }); 
// }

// window.onload=function(){
    
// }