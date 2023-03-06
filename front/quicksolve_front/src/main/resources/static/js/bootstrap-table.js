$(document).ready(function () {


    var languages = [
        { value: "af-ZA", text: "af-ZA" },
        { value: "ar-SA", text: "ar-SA" },
        { value: "ca-ES", text: "ca-ES" },
        { value: "cs-CZ", text: "cs-CZ" },
        { value: "da-DK", text: "da-DK" },
        { value: "de-DE", text: "de-DE" },
        { value: "el-GR", text: "el-GR" },
        { value: "en-US", text: "en-US" },
        { value: "es-AR", text: "es-AR" },
        { value: "es-CL", text: "es-CL" },
        { value: "es-CR", text: "es-CR" },
        { value: "es-ES", text: "es-ES" },
        { value: "es-MX", text: "es-MX" },
        { value: "es-NI", text: "es-NI" },
        { value: "es-SP", text: "es-SP" },
        { value: "et-EE", text: "et-EE" },
        { value: "eu-EU", text: "eu-EU" },
        { value: "fa-IR", text: "fa-IR" },
        { value: "fi-FI", text: "fi-FI" },
        { value: "fr-BE", text: "fr-BE" },
        { value: "fr-FR", text: "fr-FR" },
        { value: "he-IL", text: "he-IL" },
        { value: "hr-HR", text: "hr-HR" },
        { value: "hu-HU", text: "hu-HU" },
        { value: "id-ID", text: "id-ID" },
        { value: "it-IT", text: "it-IT" },
        { value: "ja-JP", text: "ja-JP" },
        { value: "ka-GE", text: "ka-GE" },
        { value: "ko-KR", text: "ko-KR" },
        { value: "ms-MY", text: "ms-MY" },
        { value: "nb-NO", text: "nb-NO" },
        { value: "nl-NL", text: "nl-NL" },
        { value: "pl-PL", text: "pl-PL" },
        { value: "pt-BR", text: "pt-BR" },
        { value: "pt-PT", text: "pt-PT" },
        { value: "ro-RO", text: "ro-RO" },
        { value: "ru-RU", text: "ru-RU" },
        { value: "sk-SK", text: "sk-SK" },
        { value: "sv-SE", text: "sv-SE" },
        { value: "th-TH", text: "th-TH" },
        { value: "tr-TR", text: "tr-TR" },
        { value: "uk-UA", text: "uk-UA" },
        { value: "ur-PK", text: "ur-PK" },
        { value: "uz-Latn-UZ", text: "uz-Latn-UZ" },
        { value: "vi-VN", text: "vi-VN" },
        { value: "zh-CN", text: "zh-CN" },
        { value: "zh-TW", text: "zh-TW" }
    ]

    var $table = $('#table')
    $table.bootstrapTable()

    var cookieLanguage = document.cookie.replace(/(?:(?:^|.*;\s*)language\s*\=\s*([^;]*).*$)|^.*$/, "$1");

    if (cookieLanguage) {
        $table.bootstrapTable('refreshOptions', {
            locale: languages.find(x => x.value.includes(cookieLanguage)).value
        })
    }else {
        var acceptLanguage = navigator.language
        $table.bootstrapTable('refreshOptions', {
            locale: languages.find(x => x.value.includes(acceptLanguage)).value
        })
    }

//Responsive table
    window.onresize = toogleTable
    window.onload = toogleTable
    window.onchange = toogleTable

    function toogleTable() {

        if (!$table.hasClass('table-responsive') && window.innerWidth < 1100) {
            $table.addClass('table-responsive')
            $table.bootstrapTable('toggleView')
        }

        if ($table.hasClass('table-responsive') && window.innerWidth > 1100) {
            $table.removeClass('table-responsive')
            $table.bootstrapTable('toggleView')
        }
    }

})