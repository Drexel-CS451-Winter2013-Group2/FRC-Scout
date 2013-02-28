Ext.onReady(function() {

    Ext.create('Ext.Viewport', {
        layout: {
            type: 'vbox',
            align: 'stretch'
        },
        defaults: {
            border: 0,
            padding: 0
        },
        items:getToolbarLinks()
    });
});

function getToolbarLinks(){
    return [{
        xtype: 'panel',
        html: '<h1>FRC Scout</h1>',
        baseCls: 'lay-title',
        height: 70
    }, {
        layout: {
            type: 'hbox',
            align: 'stretch'
        },
        defaults: {
            baseCls: 'lay-toolbar',
            padding: 4
        },
        height: 25,
        items: [{
            xtype: 'panel',
            contentEl: 'links',
            flex: 1
        }, {
            xtype: 'panel',
            contentEl: 'logout'
        }]
    },{
        xtype: 'panel',
        baseCls: 'lay-content',
        overflowX: 'auto',
        overflowY: 'auto',
        items: getContentItems(),
        flex: 1
    }];
}