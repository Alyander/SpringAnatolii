let once = 0;
$(".button-edit").click(function () {
    let elements = $(this).parents().children();
    if (once === 0) {
        $(elements[4]).html("<select class='con'><option value='IN_PROGRESS' selected>IN_PROGRESS</option><option value='DONE'>DONE</option><option value='PAUSED'>PAUSED</option></select>")
        $(elements[3]).attr("contenteditable", "true");
        $(this).text("Confirm");
        once++;
    }
    else {
       let data =  { 'id': elements[2].innerHTML, 'description': elements[3].innerHTML, 'status': elements[4].children[0].value}
        $.ajax({
            url: "/edit",
            method: "POST",
            data: JSON.stringify(data) ,
            contentType: "application/json",
            contents: data,
            dataType: "json",
            statusCode: {
                200: function() {
                    window.location.reload();
                }
            }
        })
    }
})
$(".button-delete").click(function () {
    let data = $(this).data("id")
    $.ajax({
        url: "/delete",
        method: "DELETE",
        data: String(data) ,
        dataType: "text",
        contents: String(data) ,
        contentType: "text/plain",
    })
        .done(function( msg ) {
            window.location.reload();
        });
})