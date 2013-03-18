    function getTeamAveragesStore() { 
        store1 = Ext.create('Ext.data.JsonStore', {
            fields: ['id', 'name', 'total_points', 'autonomous', 'teleop', 'climb'],
            sorters: ['name', 'total_points'],
            data: teamAveragesJSON
        });
        return store1;
    }
    
    function getTeamMatchStore() { 
        store1 = Ext.create('Ext.data.JsonStore', {
            fields: ['event_id', 'event_name', 'match_id', 'autonomous', 'teleop', 'climb', 'total_points',],
            sorters: ['event_name', 'total_points'],
            data: teamMatchJSON
        });
        return store1;
    }
    
    function getTeamChartStore() { 
        store1 = Ext.create('Ext.data.JsonStore', {
            fields: ['id', 'name', 'total_points', 'autonomous', 'teleop', 'climb'],
            sorters: ['id', 'total_points'],
            data: teamChartJSON
        });
        return store1;
    }
    
    function getRadarChartStore() {
        store1 = Ext.create('Ext.data.JsonStore', {
            fields: ['category', 'total'],
            data: teamRadarChartJSON
            });
        return store1;
    }
    
    function getTeamValue() {
        if (selectedTeamTeam != -1) 
        { return selectedTeamTeam;}
        else {
            return "";
        }
    }
    
function getGroupByTeamItems() {
    return [{
        xtype: 'combobox',
        fieldLabel: 'Select Team',
        store: getTeamStore(),
        value: getTeamValue(),
        queryMode: 'local',
        displayField: 'name',
        valueField: 'id',
        listeners: {
            select: function( combo, records, eOpts){ window.location = "/frcscout/index.jsp?teamteam=" + records[0].data.id + '&grouptab=1&teamtab=' + Ext.getCmp('teamTab').items.indexOf(Ext.getCmp('teamTab').getActiveTab()); 
            },
        }
    }, {
        xtype: 'tabpanel',
        activeTab: parseInt(getURLParameter('teamtab')),
        id: 'teamTab',
        tabPosition: 'top',
        items: [{
            title: 'Team Profile',
            items: getTeamProfileItems()
        },{
            title: 'View Matches',
            items: getTeamMatchItems()
        }]
    }];
}

function getTeamProfileItems() {
    return [{
        defaultType: 'container',
        layout: {
            type: 'vbox',
            align: 'left'
        },
        items: [{
            defaultType: 'container',
            layout: {
                type: 'hbox',
                align: 'left'
            },
            items: [{
                xtype: 'gridpanel',
                title: 'Event Points (Averaged by Match)',
                id: 'eventAverageGrid',
                store: getTeamAveragesStore(),
                columns: [
                    { header: 'Event',  dataIndex: 'name', flex: 1},
                    { header: 'Auton. Points', dataIndex: 'autonomous' },
                    { header: 'Teleop Points', dataIndex: 'teleop'},
                    { header: 'Climb Points', dataIndex: 'climb'},
                    { header: 'Total Points', dataIndex: 'total_points'}
                ],
                width: 500,
                margin: 10
            }, {
                defaultType: 'container',
                layout: {
                    type: 'vbox'
                },
                items: [{
                    xtype: 'panel',
                    border: false,
                    html: 'Team profile area',
                    width: 350,
                    margin: 10
                }, {
                    xtype: 'filefield',
                    buttonOnly: true,
                    margin: '0 0 0 100'
                }]
            }]
        }, {
            defaultType: 'container',
            layout: {
                type: 'hbox'
            },
            items: [{
                xtype: 'chart',
                defaultType: 'container',
                animate: true,
                width: 500,
                height: 300,
                margin: 25,
                store: getTeamChartStore(),
                legend: {
                    position: 'bottom'
                },
                axes: [{
                    type: 'Numeric',
                    position: 'left',
                    title: 'Points Scored',
                    grid: true,
                    minimum: 0
                },{
                    type: 'Category',
                    position: 'bottom',
                    fields: ['name'],
                    title: 'Event'
                }],
                series: [{
                    type: 'line',
                    axis: 'left',
                    xField: 'name',
                    yField: 'total_points'
                }]
            }, {
                xtype: 'chart',
                defaultType: 'container',
                width: 350,
                height: 250,
                margin: '25 0 0 0',
                animate: true,
                theme:'Category2',
                store: getRadarChartStore(),
                axes: [{
                    type: 'Radial',
                    position: 'radial',
                    label: {
                        display: true
                    }
                }],
                series: [{
                    type: 'radar',
                    xField: 'category',
                    yField: 'total',
                    showMarkers: true,
                    showInLegend: true,
                    markerConfig: {
                        radius: 5,
                        size: 5
                    },
                    style: {
                        'stroke-width': 2,
                        fill: 'none'
                    }
                }]
            }]
        }]
    }];
}

function getTeamMatchItems() {
    return [{
        defaultType: 'container',
        layout: {
            type: 'vbox',
            align: 'left'
        },
        items: [{
            defaultType: 'container',
            xtype: 'gridpanel',
            store: getTeamMatchStore(),
            columns: [
                { header: 'Event',  dataIndex: 'event_name'},
                { header: 'Match #', dataIndex: 'match_id' },
                { header: 'Auton Points', dataIndex: 'autonomous' },
                { header: 'Teleop Points', dataIndex: 'teleop'},
                { header: 'Climb Points', dataIndex: 'climb'},
                { header: 'Total Points', dataIndex: 'total_points'}
            ],
            margin: 10,
        }, {
            xtype: 'button',
            text: 'View Match Record',
            handler: function() {
                alert('Open match record with all details filled in.');
            },
            margin: '0 0 10 500'
        }]
    }];
}