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
                $('form #fuInputId').val(data.id);
                $('form #fuInputEmail').val(data.email);
                $('form #fuInputLogin').val(data.login);
                $('form #fuInputPassword').val(data.password);

                let arr = data.roles.split(',');
                let sel = $('form #fuInputRole');
                sel.empty();
                sel.append('<option selected>Choose a role</option>');
                $.each(arr, function(index, value){
                    sel.append('<option value="' + value + '">' + value + '</option>');
                });
            },
            error: function(er) {
                console.log(er);
            }
        });
    })
});
