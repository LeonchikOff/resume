let resume = {

    alert: function (message) {
        alert(message);
    },

    moreProfiles: function () {
        let page = parseInt($('#profileContainer').attr('data-profile-number')) + 1;
        let total = parseInt($('#profileContainer').attr('data-profile-total'));
        if (page >= total) {
            jQuery('#loadMoreIndicator').remove();
            jQuery('#loadMoreContainer').remove();
            return;
        }
        let url = '/fragment/more?page=' + page;
        jQuery('#loadMoreContainer').css('display', 'none');
        jQuery('#loadMoreIndicator').css('display', 'block');
        jQuery.ajax({
            url: url,
            success: function (data) {
                jQuery('#loadMoreIndicator').css('display', 'none');
                jQuery('#profileContainer').append(data);
                jQuery('#profileContainer').attr('data-profile-number', page);
                if (page >= total - 1) {
                    jQuery('#loadMoreIndicator').remove();
                    jQuery('#loadMoreContainer').remove();
                } else {
                    jQuery('#loadMoreContainer').css('display', 'block');
                }
            },
            error: function (data) {
                jQuery('#loadMoreIndicator').css('display', 'none');
                resume.alert('Error! Try again later...');
            }
        });
    }
};