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
    
    function getTeamEventsStore() { //temp data generator
        store1 = Ext.create('Ext.data.JsonStore', {
            fields: ['id', 'event1', 'event2'],
            data: [{ 'id': '1', 'event1': 30, 'event2': 29},
                   { 'id': '2', 'event1': 20, 'event2': 49},
                   { 'id': '3', 'event1': 43, 'event2': 55}
                ]
            });
        return store1;
    }
    
    function getRadarChartStore() {
        store1 = Ext.create('Ext.data.JsonStore', {
            fields: ['category', 'data'],
            data: [{ 'category': 'Auton.', 'data': 24},
                   { 'category': 'Teleop.', 'data': 50},
                   { 'category': 'Climb', 'data': 20},
                ]
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
