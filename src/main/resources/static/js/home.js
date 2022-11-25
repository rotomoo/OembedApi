$('#postData').on('click', function() {
    const url = $("#urlText").val();

    $.ajax({
        type: "GET",
        url: `/oembed?url=${url}`,
        contentType:'application/json; charset=utf-8',
        dataType: "json",
        success: function(json) {
            let titleDiv = document.querySelector("#titleDiv");
            let postUl = document.querySelector("#postUl");
            titleDiv.innerHTML = "";
            postUl.innerHTML = "";
            for(let key in json){
                if(json[key] === "Twitter") twttr.widgets.load();
                if(key === "thumbnail_url") {
                   postUl.innerHTML += "<li><div>" + key + "</div><div><img src=" + json[key] + "></div></li>";
                }
                else if(key === "title") {
                    titleDiv.innerHTML = "<div><div>" + key + "</div><div>" + json[key] + "</div></div>";
                }
                else {
                    postUl.innerHTML += "<li><div>" + key + "</div><div>" + json[key] + "</div></li>";
                }
            }
        },
        error: function(json) {
            let data = JSON.parse(json.responseText);
            alert(data.message);
        }
    });
});