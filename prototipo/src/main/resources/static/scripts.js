// 🔗 CONFIGURACIÓN DE CONEXIÓN CON TU BACKEND
const API_BASE_URL = 'http://localhost:8080/api/cliente';

let cart = [];
let customerInfo = {};

// Función para formatear precios
function formatPrice(price) {
    return new Intl.NumberFormat('es-CO', {
        style: 'currency',
        currency: 'COP',
        minimumFractionDigits: 0,
        maximumFractionDigits: 0
    }).format(price);
}

//  FUNCIÓN PRINCIPAL - CONECTAR CON TU BACKEND
async function registrarClienteEnBD(clienteData) {
    console.log(' Enviando datos al backend:', clienteData);
    
    try {
        const response = await fetch(`${API_BASE_URL}/registrar`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(clienteData)
        });

        console.log('📡 Respuesta del servidor:', response.status);
        
        if (response.ok) {
            const result = await response.json();
            console.log(' Cliente registrado exitosamente:', result);
            return { success: true, data: result };
        } else {
            // Intentar leer el error del backend
            try {
                const errorData = await response.json();
                console.error(' Error del backend:', errorData);
                return { success: false, error: errorData.mensaje || 'Error desconocido' };
            } catch {
                console.error(' Error de respuesta:', response.statusText);
                return { success: false, error: `Error ${response.status}: ${response.statusText}` };
            }
        }
    } catch (error) {
        console.error(' Error de conexión:', error);
        return { 
            success: false, 
            error: ' No se pudo conectar al servidor. Verifica que el backend esté ejecutándose en http://localhost:8080'
        };
    }
}

//  FUNCIÓN DE PRUEBA - Para verificar conexión
async function probarConexion() {
    try {
        const response = await fetch(`${API_BASE_URL}`, { method: 'GET' });
        console.log('🔗 Conexión con backend:', response.ok ? 'OK' : ' ERROR');
        return response.ok;
    } catch (error) {
        console.error(' Backend no disponible:', error);
        return false;
    }
}

// Funciones del carrito (sin cambios)
function addToCart(name, price) {
    const existingItem = cart.find(item => item.name === name);
    
    if (existingItem) {
        existingItem.quantity += 1;
    } else {
        cart.push({
            name: name,
            price: price,
            quantity: 1
        });
    }
    
    updateCartCount();
    
    // Animación de confirmación
    const button = event.target;
    const originalText = button.textContent;
    button.textContent = '¡Agregado! ✓';
    button.style.background = '#4CAF50';
    
    setTimeout(() => {
        button.textContent = originalText;
        button.style.background = 'linear-gradient(45deg, #4ecdc4, #44a08d)';
    }, 1000);
}

function updateCartCount() {
    const totalItems = cart.reduce((sum, item) => sum + item.quantity, 0);
    document.getElementById('cart-count').textContent = totalItems;
}

function openCart() {
    displayCartItems();
    document.getElementById('cart-modal').style.display = 'block';
}

function closeCart() {
    document.getElementById('cart-modal').style.display = 'none';
}

function displayCartItems() {
    const cartItemsDiv = document.getElementById('cart-items');
    const cartTotalDiv = document.getElementById('cart-total');
    
    if (cart.length === 0) {
        cartItemsDiv.innerHTML = '<p style="text-align: center; color: #666; padding: 40px;">Tu carrito está vacío</p>';
        cartTotalDiv.innerHTML = '';
        return;
    }
    
    let itemsHTML = '';
    let total = 0;
    
    cart.forEach((item, index) => {
        const itemTotal = item.price * item.quantity;
        total += itemTotal;
        
        itemsHTML += `
            <div class="cart-item">
                <div>
                    <strong>${item.name}</strong><br>
                    <span style="color: #666;">${formatPrice(item.price)} x ${item.quantity}</span>
                </div>
                <div>
                    <span style="font-weight: bold; margin-right: 15px;">${formatPrice(itemTotal)}</span>
                    <button class="remove-item" onclick="removeFromCart(${index})">Eliminar</button>
                </div>
            </div>
        `;
    });
    
    cartItemsDiv.innerHTML = itemsHTML;
    cartTotalDiv.innerHTML = `Total: ${formatPrice(total)}`;
}

function removeFromCart(index) {
    cart.splice(index, 1);
    updateCartCount();
    displayCartItems();
}

function proceedToCheckout() {
    if (cart.length === 0) {
        alert('Tu carrito está vacío');
        return;
    }
    
    closeCart();
    document.getElementById('register-modal').style.display = 'block';
}

function closeRegister() {
    document.getElementById('register-modal').style.display = 'none';
}

function closeInvoice() {
    document.getElementById('invoice-modal').style.display = 'none';
}

// Indicadores de carga
function mostrarCarga() {
    const submitBtn = document.querySelector('#register-form button[type="submit"]');
    submitBtn.textContent = ' Guardando en base de datos...';
    submitBtn.disabled = true;
}

function ocultarCarga() {
    const submitBtn = document.querySelector('#register-form button[type="submit"]');
    submitBtn.textContent = 'Confirmar Compra';
    submitBtn.disabled = false;
}

//  EVENTO PRINCIPAL - REGISTRO DEL CLIENTE
document.addEventListener('DOMContentLoaded', function() {
    // Probar conexión al cargar la página
    probarConexion();
    
    document.getElementById('register-form').addEventListener('submit', async function(e) {
        e.preventDefault();
        
        console.log(' Iniciando proceso de registro...');
        
        mostrarCarga();
        
        //  RECOPILAR DATOS DEL FORMULARIO
        const clienteData = {
            nombre: document.getElementById('name').value.trim(),
            email: document.getElementById('email').value.trim(),
            telefono: document.getElementById('phone').value.trim(),
            direccion: document.getElementById('address').value.trim()
        };
        
        console.log(' Datos del cliente:', clienteData);
        
        // Validaciones básicas del frontend
        if (!clienteData.nombre || !clienteData.email || !clienteData.telefono || !clienteData.direccion) {
            alert(' Por favor completa todos los campos');
            ocultarCarga();
            return;
        }
        
        //  REGISTRAR EN LA BASE DE DATOS
        const resultado = await registrarClienteEnBD(clienteData);
        
        ocultarCarga();
        
        if (resultado.success) {
            //  ÉXITO
            customerInfo = clienteData;
            
            alert(' ¡Cliente registrado exitosamente en la base de datos!');
            console.log(' Datos guardados:', resultado.data);
            
            // Continuar con el flujo normal
            generateInvoice();
            closeRegister();
            document.getElementById('register-modal').style.display = 'none';
            document.getElementById('invoice-modal').style.display = 'block';
            
            // Limpiar carrito y formulario
            cart = [];
            updateCartCount();
            document.getElementById('register-form').reset();
            
        } else {
            //  ERROR
            console.error(' Error en el registro:', resultado.error);
            alert(` Error al registrar cliente:\n\n${resultado.error}\n\n Verifica que el backend esté ejecutándose`);
        }
    });
});

function generateInvoice() {
    const invoiceNumber = 'INV-' + Date.now();
    const currentDate = new Date().toLocaleDateString('es-ES');
    const currentTime = new Date().toLocaleTimeString('es-ES');
    
    let total = 0;
    let itemsHTML = '';
    
    cart.forEach(item => {
        const itemTotal = item.price * item.quantity;
        total += itemTotal;
        
        itemsHTML += `
            <div class="invoice-item">
                <div>
                    <strong>${item.name}</strong><br>
                    <small>Cantidad: ${item.quantity} x ${formatPrice(item.price)}</small>
                </div>
                <div><strong>${formatPrice(itemTotal)}</strong></div>
            </div>
        `;
    });
    
    const invoiceHTML = `
        <div class="invoice">
            <div class="invoice-header">
                <h1> Opacre Food</h1>
                <h2>FACTURA DE COMPRA</h2>
                <p><strong>Factura #:</strong> ${invoiceNumber}</p>
                <p><strong>Fecha:</strong> ${currentDate} - ${currentTime}</p>
            </div>
            
            <div class="invoice-details">
                <h3>Datos del Cliente:</h3>
                <p><strong>Nombre:</strong> ${customerInfo.nombre}</p>
                <p><strong>Email:</strong> ${customerInfo.email}</p>
                <p><strong>Teléfono:</strong> ${customerInfo.telefono}</p>
                <p><strong>Dirección:</strong> ${customerInfo.direccion}</p>
            </div>
            
            <div class="invoice-items">
                <h3>Artículos Comprados:</h3>
                ${itemsHTML}
            </div>
            
            <div class="invoice-total">
                <strong>TOTAL: ${formatPrice(total)}</strong>
            </div>
            
            <div style="text-align: center; margin-top: 30px; color: #666;">
                <p>¡Gracias por tu compra!</p>
                <p>Tu pedido será procesado en las próximas 24 horas.</p>
                <p><strong> Cliente registrado en nuestra base de datos</strong></p>
            </div>
        </div>
    `;
    
    document.getElementById('invoice-content').innerHTML = invoiceHTML;
}

function printInvoice() {
    const printContent = document.getElementById('invoice-content').innerHTML;
    const originalContent = document.body.innerHTML;
    
    document.body.innerHTML = printContent;
    window.print();
    document.body.innerHTML = originalContent;
    
    location.reload();
}

// Cerrar modales al hacer clic fuera
window.onclick = function(event) {
    const cartModal = document.getElementById('cart-modal');
    const registerModal = document.getElementById('register-modal');
    const invoiceModal = document.getElementById('invoice-modal');
    
    if (event.target === cartModal) {
        closeCart();
    } else if (event.target === registerModal) {
        closeRegister();
    } else if (event.target === invoiceModal) {
        closeInvoice();
    }
}