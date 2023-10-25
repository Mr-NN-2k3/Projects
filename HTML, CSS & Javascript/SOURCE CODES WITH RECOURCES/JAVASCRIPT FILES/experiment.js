const folderPath = './RECOURCES/IMAGE FILES/';

const xhr = new XMLHttpRequest();
xhr.open('GET', folderPath, true);
xhr.onload = function() {
  if (xhr.status === 200) {
    const files = xhr.responseText.split('\n');
    const imgsPerUl = Math.ceil(files.length / 3); // Calculate number of images per <ul>
    let imgsCount = 79; // Keep track of how many images have been processed

    for (let i = 0; i < 3; i++) { // Loop over 3 <ul> elements
      const ul = document.createElement('ul');
      ul.classList.add('ul');
      document.querySelector('.glry').appendChild(ul); // Append <ul> to .glry div

      for (let j = 0; j < imgsPerUl && imgsCount < files.length; j++) { // Loop over <li> elements
        const file = files[imgsCount];
        const filePath = folderPath + file;
        const fileExt = file.split('.').pop().toLowerCase();

        if (fileExt === 'jpg' || fileExt === 'png' || fileExt === 'gif') {
          const li = document.createElement('li');
          const imgTag = `<img src="${filePath}" alt="${file}">`;
          li.innerHTML = imgTag;
          ul.appendChild(li);
          imgsCount++;
        }
      }
    }
  } else {
    console.error(xhr.statusText);
  }
};
xhr.onerror = function() {
  console.error(xhr.statusText);
};
xhr.send();
