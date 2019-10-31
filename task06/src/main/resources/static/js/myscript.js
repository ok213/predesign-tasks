$(document).ready(function() {
    $('td button').click(function(e) {
        e.preventDefault();
        let url = "/admin/update/" + $(this).val();
        $.ajax({
            method: "GET",
            url: url,
            dataType: "json",
            success: function(data) {
                // console.log(data);
                let form = $('#formUpdate');
                let user = data[0];
                $('form #fuInputId').val(user.id);
                $('form #fuInputEmail').val(user.email);
                $('form #fuInputLogin').val(user.login);
                $('form #fuInputPassword').val(user.password);

                let sel = $('form #fuInputRole');
                let roles = data[1];
                sel.empty();
                $.each(roles, function(index, value){
                    // console.log("id: " + value.id + "  role: " + value.role);
                    let selected = value.role == 'ROLE_USER'? 'selected' : "";
                    sel.append(`<option ${selected} value="${value.id}">${value.role}</option>`);
                });
            },
            error: function(er) {
                console.log(er);
            }
        });
    })
});
