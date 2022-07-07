import React from "react";
import '../../styles/TexFieldBalance.css';
import  {Validator} from './Validator';
function TextFieldBalance() {
    return (
        <>
            <div class="main">
                <form action="" method="POST" class="form" id="form-1">

                    <div class="form-group">
                        <label for="field-1" class="form-label">
                            field
                        </label>
                        <input
                            id="field-1"
                            name="field-1"
                            type="text"
                            placeholder="field-1"
                            class="form-control"
                        />
                        <span class="form-message"></span>
                    </div>

                    <div class="form-group">
                        <label for="field-2" class="form-label">
                            field
                        </label>
                        <input
                            id="field-2"
                            name="field-2"
                            type="text"
                            placeholder="field-2"
                            class="form-control"
                        />
                        <span class="form-message"></span>
                    </div>

                    <div class="form-group">
                        <label for="field-3" class="form-label">
                            field
                        </label>
                        <input
                            id="field-3"
                            name="field-3"
                            type="text"
                            placeholder="field-3"
                            class="form-control"
                        />
                        <span class="form-message"></span>
                    </div>

                    <div class="form-group">
                        <label for="field-4" class="form-label">
                            field
                        </label>
                        <input
                            id="field-4"
                            name="field-4"
                            placeholder="field4"
                            type="text"
                            class="form-control"
                        />
                        <span class="form-message"></span>
                    </div>

                    <button class="form-submit">Đăng ký</button>
                </form>
            </div>
        </>
    );
}
Validator({
    form: "#form-1",
    errorSelector: ".form-message",
    rules: [
        Validator.isRequired("#field-1"),
        Validator.isRequired("#field-2"),
        Validator.isRequired("#field-3"),
        Validator.isRequired("#field-4")
    ]
});

export default TextFieldBalance;
