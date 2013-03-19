    function getEventStore() { 
        store1 = Ext.create('Ext.data.JsonStore', {
            fields: ['id', 'name', 'location', 'start_date', 'end_date'],
            data: eventJSON
        });
        return store1;
    }
    
    function getTeamStore() { 
        store1 = Ext.create('Ext.data.JsonStore', {
            fields: ['id', 'name', 'location'],
            data: teamJSON
        });
        return store1;
    }
    
function getContentItems(){
    return [{
        xtype: 'tabpanel',
        activeTab: parseInt(getURLParameter('grouptab')),
        tabPosition: 'top',
        overflowY: 'auto',
        overflowX: 'auto',
        minWidth: 800,
        items: [{
            title: 'Group By Event',
            padding: 10,
            items: getGroupByEventItems()
        },{
            title: 'Group By Team',
            padding: 10,
            items: getGroupByTeamItems()
        }]
    }];
}
