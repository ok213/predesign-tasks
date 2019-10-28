$(document).ready(function() {
    $('td button').click(function(e) {
        e.preventDefault();

        let btn = $(this);
        let url = "/admin/update/" + btn.val();

        $.ajax({
            method: "GET",
            url: url,
            dataType: "json",
            data: {

            },
            success: function(data) {
                console.log(data);
            },
            error: function(er) {
                console.log(er);
            }
        });
    })
});
