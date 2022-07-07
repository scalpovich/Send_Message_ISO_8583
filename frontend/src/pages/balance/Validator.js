export function Validator(options){

    //ham thuc hien validate
    function validate (inputElement, rule){
        var errorMessage = rule.test(inputElement.value);
        var errorElement = inputElement.parentElement.querySelector(options.errorSelector);

        if (errorMessage){
            errorElement.innerText = errorMessage;
            inputElement.parentElement.classList.add('invalid');
        }else{
            errorElement.innerText = '';
            inputElement.parentElement.classList.remove('invalid');
        }
    }

    //lay element cua form
    var formElement = document.querySelector(options.form);
    if(formElement){
        options.rules.forEach(function (rule) {
            var inputElement = formElement.querySelector(rule.selector);

            if(inputElement){
                inputElement.onblur = function(){
                    validate(inputElement, rule)
                }
                inputElement.oninput = function(){
                    var errorElement = inputElement.parentElement.querySelector(options.errorSelector);
                    errorElement.innerText = '';
                    inputElement.parentElement.classList.remove('invalid');
                }
            }
        });
    }
}

//dinh nghia rules
Validator.isRequired = function (selector){
    return {
        selector :selector,
        test: function(value){
            return value.trim() ? undefined : 'Vui long nhap dung truong nay';
        }
    }
}
