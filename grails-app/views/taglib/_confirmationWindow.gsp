<g:form action="${action}" controller="${controller}" params="${params}">
    <div id="${id}" class="modal hide fade" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>

            <h3>${title}</h3>
        </div>

        <div class="modal-body">
            <p>${message}</p>
            ${raw(body)}
        </div>

        <div class="modal-footer">
            <button class="btn" data-dismiss="modal" aria-hidden="true">
                ${cancelLabel}
            </button>
            <button class="btn btn-primary">${confirmLabel}</button>
        </div>
    </div>
</g:form>
