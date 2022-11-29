class App {
    static DOMAIN_SERVER = "http://localhost:8080";
    static PROVINCE_API = "https://vapi.vnappmob.com/api/province/";
    static DISTRICT_API = this.PROVINCE_API + "district/";
    static WARD_API = this.PROVINCE_API + "ward/";
    static AUTH_URL = this.DOMAIN_SERVER + "/api/auth";
    static ROLE_API = this.DOMAIN_SERVER + "/api/roles";



    static SERVER_CLOUDINARY = "https://res.cloudinary.com/docwqc9fw/image/upload";
    static SCALE_W250_H250_Q100 = "/c_limit,w_250,h_250,q_100";
    static SCALE_W150_H150_Q100 = "/c_limit,w_70,h_70,q_100";
    static SCALE_W160_H160_Q100 = "/c_limit,w_160,h_160,q_100";

    static PRODUCTS_API = this.DOMAIN_SERVER + "/api/products";
    static CUSTOMER_API = this.DOMAIN_SERVER + "/api/customers";

    static AlertMessageVi = class {
        static SUCCESS_CREATED = "Tạo dữ liệu thành công !";
        static SUCCESS_UPDATED = "Cập nhật dữ liệu thành công !";


        static ERROR_400 = "Thao tác không thành công, vui lòng kiểm tra lại dữ liệu.";
        static ERROR_401 = "Unauthorized - Access Token của bạn hết hạn hoặc không hợp lệ.";
        static ERROR_403 = "Forbidden - Bạn không được quyền truy cập tài nguyên này.";
        static ERROR_404 = "Not Found - Tài nguyên này đã bị xóa hoặc không tồn tại";
        static ERROR_500 = "Internal Server Error - Hệ thống Server đang có vấn đề hoặc không truy cập được.";

        static ERROR_ID_NOTFOUND = "Người dùng không tồn tại";

    }


    static SweetAlert = class {
        static showDeactivateConfirmDialog() {
            return Swal.fire({
                icon: 'warning',
                text: 'Are you sure to deactivate the selected customer ?',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, please deactivate this client !',
                cancelButtonText: 'Cancel',
            })
        }



        static showAlertSuccess(t) {
            Swal.fire({
                icon: 'success',
                title: t,
                position: 'top-end',
                showConfirmButton: false,
                timer: 1500
            })
        }

        static showAlertError(t) {
            Swal.fire({
                icon: 'error',
                title: 'Warning',
                text: t,
            })
        }

        static showError401() {
            Swal.fire({
                icon: 'error',
                title: 'Truy cập bị từ chối',
                text: 'Thông tin không hợp lệ!',
            })
        }
        static showError404() {
            Swal.fire({
                icon: 'error',
                title: 'Truy cập bị từ chối',
                text: 'Not Found - Tài nguyên này đã bị xóa hoặc không tồn tại!',
            })
        }
        static showError500() {
            Swal.fire({
                icon: 'error',
                title: 'Truy cập bị từ chối',
                text: ' Internal Server Error - Hệ thống Server đang có vấn đề hoặc không truy cập được.',
            })
        }


        static showError403() {
            Swal.fire({
                icon: 'error',
                title: 'Truy cập bị từ chối',
                text: 'Bạn không được phép thực hiện chức năng này!',
            })
        }
    }

    static IziToast = class {
        static showSuccessAlertLeft(m) {
            iziToast.success({
                title: 'Success',
                position: 'topRight',
                timeout: 2500,
                message: m
            });
        }
        static showSuccessAlertRight(m) {
            iziToast.success({
                title: 'Success',
                position: 'topRight',
                timeout: 2500,
                message: m
            });
        }

        static showErrorAlertLeft(m) {
            iziToast.error({
                title: 'Error',
                position: 'topRight',
                timeout: 2500,
                message: m
            });
        }
        static showErrorAlertRight(m) {
            iziToast.error({
                title: 'Error',
                position: 'topRight',
                timeout: 2500,
                message: m
            });
        }

    }

    static Notify = class {
        static showSuccessAlert(m) {
            $.notify(m, "success");
        }

        static showErrorAlert(m) {
            $.notify(m, "error");
        }
    }

    static renderRowProductIndex(obj, avatar) {
        let str = `
        <div class="col-12 col-md-6 col-lg-3" >
<div class="row product-item">
<div class="col-12 p-item-img">
 <img src="${this.SERVER_CLOUDINARY}/${this.SCALE_W160_H160_Q100}/${avatar.fileFolder}/${avatar.fileName}" alt="" >
<div class="p-item-overlay text-center d-flex justify-content-center align-items-center">
<div class="btn-container">
<button class="btn our-btn q-btn rounded-pill" onclick="open_model_window1('model-window5');">QUICK VIEW</button>
<a class="btn our-btn btn-gradient rounded-pill" href="shop-cart.html">ADD TO CART</a>
</div>
</div>
</div>
<div class="col-12 p-item-detail">
<p class="text-center p-item-price"> ${new Intl.NumberFormat('vi-VN', {style: 'currency', currency: 'VND'}).format(obj.price)}</p>
<p class="text-center p-item-price">${obj.title}</p>
</div>
</div>
</div>
        `;

        return str;
    }
}
class User{
    constructor(username,password,role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
class Role {
    constructor(id,code) {
        this.id = id;
        this.code = code;
    }
}


class LocationRegion {
    constructor(id, provinceId, provinceName, districtId, districtName, wardId, wardName, address) {
        this.id = id;
        this.provinceId = provinceId;
        this.provinceName = provinceName;
        this.districtId = districtId;
        this.districtName = districtName;
        this.wardId = wardId;
        this.wardName = wardName;
        this.address = address;
    }
}

class Customer {
    constructor(id, fullName, email, phone, locationRegion, deleted, avatarDTO) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.locationRegion = locationRegion;
        this.deleted = deleted;
        this.avatarDTO = avatarDTO;
    }
}


class CustomerAvatar{
    constructor(id, fileName, fileFolder, fileUrl, fileType, cloudId, ts, customer) {
        this.id = id;
        this.fileName = fileName;
        this.fileFolder = fileFolder;
        this.fileUrl = fileUrl;
        this.fileType = fileType;
        this.cloudId = cloudId;
        this.ts = ts;
        this.customer = customer;
    }
}
class Product {
    constructor(id, title, price,quantity, description, avatar) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.avatar = avatar;
    }
}
class Avatar {
    constructor(id, fileName, fileFolder, fileUrl, cloudId, fileType) {
        this.id = id;
        this.fileName = fileName;
        this.fileFolder = fileFolder;
        this.fileUrl = fileUrl;
        this.cloudId = cloudId;
        this.fileType = fileType;
    }
}
class ProductAvatar {
    constructor(id, fileName, fileFolder, fileUrl, fileType, cloudId, ts) {
        this.id = id;
        this.fileName = fileName;
        this.fileFolder = fileFolder;
        this.fileUrl = fileUrl;
        this.fileType = fileType;
        this.cloudId = cloudId;
        this.ts = ts;
    }
}