const scene = new THREE.Scene()


// ✅ Neutral light background (studio gray)

// =====================
// CAMERA
// =====================
const camera = new THREE.PerspectiveCamera(
  60,
  window.innerWidth / window.innerHeight,
  0.1,
  1000
)
camera.position.set(0, 2.5, 6)

// =====================
// RENDERER
// =====================
const renderer = new THREE.WebGLRenderer({
  antialias: true,
  alpha: true,   // ✅ REQUIRED for RGBA
})


// ✅ EXACT rgba(255, 214, 170, 0.904)





// ✅ Correct color space for realism
renderer.outputColorSpace = THREE.SRGBColorSpace
renderer.toneMapping = THREE.ACESFilmicToneMapping
renderer.toneMappingExposure = 1.25

const container = document.getElementById('watch-container')
container.appendChild(renderer.domElement)

function resizeRenderer() {
  const width = container.clientWidth
  const height = container.clientHeight

  renderer.setSize(width, height)
  camera.aspect = width / height
  camera.updateProjectionMatrix()
}


resizeRenderer()

// =====================
// CONTROLS
// =====================
new OrbitControls(camera, renderer.domElement)

// Global soft ambient so nothing is pitch black


// Global soft ambient so nothing is pitch black


// ✅ STRONG RIM LIGHT (back highlight on metal edges)
// Soft global ambience
const ambientLight = new THREE.AmbientLight(0xffffff, 0.25)
scene.add(ambientLight)

// Strong key light (front-right)
const keyLight = new THREE.DirectionalLight(0xffffff, 3.5)
keyLight.position.set(6, 8, 6)
scene.add(keyLight)

// Soft fill (front-left)
const fillLight = new THREE.DirectionalLight(0xffffff, 1.5)
fillLight.position.set(-6, 4, 4)
scene.add(fillLight)

// Strong rim highlight (back edge glow)
const rimLight = new THREE.DirectionalLight(0xffffff, 2.2)
rimLight.position.set(0, 6, -8)
scene.add(rimLight)

// Dial spotlight (face readability)
const dialLight = new THREE.SpotLight(
  0xffffff,
  5,
  25,
  Math.PI / 9,
  0.3
)
dialLight.position.set(0, 7, 4)
dialLight.target.position.set(0, 1, 0)
scene.add(dialLight)
scene.add(dialLight.target)

// =====================
// WATCH LOADER (ORIGINAL MATERIALS PRESERVED)
// =====================
const gltfLoader = new GLTFLoader()
let Hourhand = null
let handPivot = null

const MODEL_URL = '/LureCo/resources/fish/models/handhour.glb'

gltfLoader.load(MODEL_URL, (gltf) => {
  const watch = gltf.scene
  watch.scale.set(0.5, 0.5, 0.5)
  watch.position.set(0,0.8,0.0)
  scene.add(watch)

  watch.traverse((obj) => {
  if (obj.isMesh && obj.material) {
    obj.material.envMapIntensity = 1.2
    obj.material.needsUpdate = true
  }
})



  Hourhand = watch.getObjectByName('Hourhand') || watch.getObjectByName('Hand_hour')
  Hourhand.scale.set(0.6,0.6,0.6)
  if (!Hourhand) return

  console.log('✅ HOUR HAND FOUND:', Hourhand)

  // ✅ Create a pivot at the REAL WATCH CENTER
  handPivot = new THREE.Object3D()
  handPivot.position.set(-0, 0, 0) // ✅ YOUR WATCH CENTER
  scene.add(handPivot)

  // ✅ Move Hourhand into the pivot
  Hourhand.position.sub(handPivot.position)
  handPivot.add(Hourhand)

  
})



// =====================
// RESIZE
// =====================


// =====================
// STATS
// =====================
const stats = new Stats()
document.body.appendChild(stats.dom)

// =====================
// ANIMATION LOOP
// =====================
const speed = 0.01

function animate() {
  requestAnimationFrame(animate)

  if (handPivot) {
    // ✅ REAL WATCH ROTATION — JUST LIKE BLENDER
    handPivot.rotation.y += speed
  }

  renderer.render(scene, camera)
  stats.update()
}

animate()