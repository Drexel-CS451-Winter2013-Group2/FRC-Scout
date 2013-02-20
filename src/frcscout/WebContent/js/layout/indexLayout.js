function getContentItems(){
    return [{
        xtype: 'tabpanel',
        activeTab: 0,
        tabPosition: 'top',
        items: [{
            title: 'Overview',
            html: '<p> tab 1 </p>'
        },{
            title: 'View Match',
            html: '<p> tab2 </p>'
        },{
            title: 'View Team',
            html: '<p> tab3 </p>'
        }]
    }];
}