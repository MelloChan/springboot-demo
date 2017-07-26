//服务端接口
var server = {
    getMessageList: function(cb, onerror){
        axios.get('/message/list')
            .then(cb).catch(onerror || function(){});
    },
    postMessage: function(content, email, cb, onerror){
        axios.post('/message/', {
            content: content,
            replyEmail: email
        })
            .then(cb).catch(onerror || function(){});
    },
    replyMessage: function(id, email, cb, onerror){
        axios.post('/question/', {
            id: id,
            replyEmail: email
        })
            .then(cb).catch(onerror || function(){});
    }
};

//数据存储
var localdata = {
    data: {},
    put: function(key, value){
        this.data[key] = value;
    },
    add: function(key, value){
        if (this.data[key]){
            this.data[key].push(value);
        } else {
            this.data[key] = [value];
        }
    }
};

//模板
var articleItemTpl = "<div class=\"article-item\" data-id=\"${id}\">" +
    "<div class=\"article-item-content\">" +
    "<span class=\"article-item-no\">${number}</span>" + "${content}" +
    "</div>" +
    "<div class=\"article-item-opera\">" +
    "<input placeholder=\"请输入你的疑问以及邮箱\" class=\"article-item-email\"/>" +
    "<div class=\"article-item-ask\" data-id=\"${id}\">" +
    "提交疑问" +
    "</div>" +
    "</div>" +
    "</div>";

var utils = {
    escapeHtml: function(str){
        if(typeof(str)!=='string'){return null;}
        return str.replace(/&/gm, "&amp;").replace(/</gm, "&lt;").replace(/>/gm, "&gt;").replace(/"/gm, "&quot;")
            .replace(/'/gm, "&#39;");
    },
    compileByObj: function (tpl, obj, isInNullStr) {
        var hasOwn = {}.hasOwnProperty;
        if (tpl && obj) {
            var _this = this;
            return tpl.replace(/\$\{(={0,1}.+?)\}/g, function ($1, $2) {
                if($2!=null){
                    var key = $2.trim(),
                        noEscaple = key.indexOf('=') === 0;
                    if(noEscaple){
                        key = key.substring(1, key.length);
                    }
                    if (hasOwn.call(obj, key)) {
                        return noEscaple ? (obj[key]+'') : utils.escapeHtml(obj[key]+'');
                    } else {
                        if (isInNullStr) {
                            return '';
                        }
                    }
                }
                return $1;
            });
        }
        return tpl;
    },
};

//业务逻辑

var biz = {
    allItemNoPlusPlus: function(){
        if (document.querySelectorAll){
            var nodelists = document.querySelectorAll('.article-item-no');
            for (var i = 0,len = nodelists.length; i < len; i++){
                nodelists[i].innerText = parseInt(nodelists[i].innerText) + 1;
            }
        } else if (document.getElementsByClassName){
            document.document.getElementsByClassName('.article-item-no').forEach(function(dItem){
                dItem.innerText = parseInt(dItem.innerText) + 1;
            });
        } else {
            var tags=document.getElementsByTagName('div');
            var tagArr = [];
            for (var i = 0, len = tags.length; i < len; i++){
                if (tags[i].className.indexOf('.article-item-no') !== 0){
                    tagArr.push(tags[i]);
                }
            }
            tagArr.forEach(function(dItem){
                dItem.innerText = parseInt(dItem.innerText) + 1;
            });
        }
    },
    submitIssue: function(cb){
        var formDom = document.querySelector('.ask-form textarea');
        var emailDom = document.querySelector('.ask-form-email');
        var formData = formDom.value;
        var emailData = emailDom.value;
        if (formData.trim() == ""){
            return alert('请输入分享的内容');
        }
        if (emailData.trim() == ""){
            return alert('请输入联系邮箱');
        }
        server.postMessage(formData, emailData, function(res){
            var id = res.data;
            var str = utils.compileByObj(articleItemTpl, {
                number: 1,
                id: id,
                content: formData
            });
            var lists = document.querySelector('.article-list');
            biz.allItemNoPlusPlus();
            var c = document.createElement('div');
            c.innerHTML = str;
            lists.insertBefore(c.childNodes[0], lists.childNodes[0]);
            formDom.value = '';
            emailDom.value = '';
            cb && cb();
            alert('提交分享成功');
        });
    },
    submitReply: function(id, email, cb){
        server.replyMessage(id, email, function(){
            cb && cb();
            alert('提交疑问成功');
        });
    }
}

server.getMessageList(function(res){
    var str = "";
    localdata.put("messageList", res.data);
    res.data.forEach(function(d, idx){
        str += utils.compileByObj(articleItemTpl, {
            number: idx + 1,
            id: d.id,
            content: d.content
        });
    });

    document.querySelector('.article-list').innerHTML = str;
    bizListener();
});

function bizListener(){
    setTimeout(function(){
        document.getElementsByTagName('body')[0].removeChild(document.getElementById('cover'));
    }, 1000);
    var askBtn = document.querySelector('.ask');
    var askForm = document.querySelector('.ask-form');
    var t = null;

    document.querySelector('.article-list').addEventListener('click', function(e){
        if (e.target.className == 'article-item-ask'){
            var id = e.target.getAttribute('data-id');
            var email = e.target.parentNode.childNodes[0].value;
            if (email.trim() == ""){
                return alert('请输入联系邮箱');
            }
            biz.submitReply(id, email, function(){
                e.target.parentNode.childNodes[0].value = "";
            });
        }
    });

    askBtn.addEventListener('click', function(e){
        if (this.innerText === '提交分享'){
            biz.submitIssue(function(){
                backAskBtn();
            });
            return;
        }
        this.innerText = '提交分享';
        t && clearInterval(t) && (t = null);
        var scrollUnit = document.body.scrollTop / 80;
        t = setInterval(function(){
            if (document.body.scrollTop - scrollUnit <= 0){
                document.body.scrollTop = 0;
                clearInterval(t);
                t = null;
            } else {
                document.body.scrollTop -= scrollUnit;
            }
        });
        askForm.className = askForm.className + ' ask-form-show';
    });

    window.addEventListener('scroll', function(){
        if (this.pageYOffset > 150 && !t){
            backAskBtn();
        }
    });

    function backAskBtn(){
        askBtn.innerText = '我要分享';

        var clsIdx = askForm.className.indexOf('ask-form-show');

        if (clsIdx !== -1){
            askForm.className = askForm.className.substr(0, askForm.className.indexOf('ask-form-show'));
        }
    }
}
