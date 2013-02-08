Ext.onReady(function() {

    Ext.create('Ext.Viewport', {
        layout: {
            type: 'vbox',
            align: 'stretch'
        },
        defaults: {
            border: 0
        },
        items:getToolbarLinks()
    });
});

function getToolbarLinks(){
    return [{
        xtype: 'panel',
        html: '<h1>FRC Scout</h1>',
        baseCls: 'lay-title',
        height: 70,
        padding: 10
    }, {
        xtype: 'panel',
        baseCls: 'lay-toolbar',
        html: '<a href="#">View Data</a> | <a href="#">Manage My Scout Data </a> | <a href="#">System Administrator</a>',
        height: 25,
        padding: 3
    },{
        xtype: 'panel',
        baseCls: 'lay-content',
        contentEl:'content',
        flex: 1
    }]
}