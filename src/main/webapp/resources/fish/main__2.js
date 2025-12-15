fetch("/LureCo/resources/fish/l.svg")
  .then(res => res.text())
  .then(svg => {
    document.getElementById("logo").innerHTML = svg;
  });
  
  fetch("/LureCo/resources/fish/fish-background.svg")
    .then(res => res.text())
    .then(svg => {
      document.getElementById("logo--2").innerHTML = svg;
    });