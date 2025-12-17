const notificationsContainer = document.createElement("div");
notificationsContainer.className = "notifications";
document.body.appendChild(notificationsContainer);

function createToast(type, icon, title, text) {
    const toast = document.createElement("div");
    toast.className = `toast ${type}`;

    toast.innerHTML = `
        <i class="${icon}"></i>
        <div class="content">
            <div class="title">${title}</div>
            <span>${text}</span>
        </div>
        <i class="fa-solid fa-xmark close"></i>
    `;

    notificationsContainer.appendChild(toast);

    toast.querySelector(".close").onclick = () => toast.remove();

    setTimeout(() => toast.remove(), 2500);
}

window.showSuccess = (msg, title = "Thành công") =>
    createToast("success", "fa-solid fa-circle-check", title, msg);

window.showError = (msg, title = "Lỗi") =>
    createToast("error", "fa-solid fa-circle-exclamation", title, msg);

window.showWarning = (msg, title = "Cảnh báo") =>
    createToast("warning", "fa-solid fa-triangle-exclamation", title, msg);

window.showInfo = (msg, title = "Thông tin") =>
    createToast("info", "fa-solid fa-circle-info", title, msg);