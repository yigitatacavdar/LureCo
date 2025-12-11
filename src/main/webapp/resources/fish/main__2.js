fetch("/LureCo/resources/fish/l.svg")
  .then(res => res.text())
  .then(svg => {
    document.getElementById("logo").innerHTML = svg;
  });