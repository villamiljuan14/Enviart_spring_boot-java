const carousel = document.getElementById('carousel');
const slides = carousel.children;
const total = slides.length;
let index = 0;

// Ajustar ancho del contenedor y de cada slide dinámicamente
carousel.style.width = `${total * 100}%`;
for (let slide of slides) {
  slide.style.width = `${100 / total}%`;
}

function showSlide(i) {
  const offset = -i * (100 / total);
  carousel.style.transform = `translateX(${offset}%)`;
}

document.getElementById('next').addEventListener('click', () => {
  index = (index + 1) % total;
  showSlide(index);
});

document.getElementById('prev').addEventListener('click', () => {
  index = (index - 1 + total) % total;
  showSlide(index);
});

// Auto-slide cada 6 segundos
setInterval(() => {
  index = (index + 1) % total;
  showSlide(index);
}, 6000);

// Inicializar posición
showSlide(index);
