
//choose

const fileUploader = document.getElementById('pj_images');
const content = document.getElementById('images');
const reader = new FileReader();

fileUploader.addEventListener('change', (event) => {
    const files = event.target.files;
    console.log('files', files);
    content.innerHTML = '';
    const img = document.createElement('img');
    img.style.height = '100px';
    img.style.width = '100px';
    content.appendChild(img);
    img.src = files;
  
  // show the upload feedback
    const feedback = document.getElementById('feedback');
    const msg = `${files.length} file(s) uploaded successfully!`;
    feedback.innerHTML = msg;
});



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