package org.cujau.swt.calendar;import java.text.ParseException;import java.text.SimpleDateFormat;import java.util.Date;import org.cujau.swt.calendar.SWTCalendarEvent;import org.cujau.swt.calendar.SWTCalendarListener;import org.eclipse.swt.SWT;import org.eclipse.swt.layout.FillLayout;import org.eclipse.swt.widgets.Button;import org.eclipse.swt.widgets.Display;import org.eclipse.swt.widgets.Event;import org.eclipse.swt.widgets.Listener;import org.eclipse.swt.widgets.Shell;import org.eclipse.swt.widgets.Text;import org.junit.Ignore;@Ignorepublic class CalendarComboDialogDemo {    public static void main( String[] args ) throws ParseException {        final SimpleDateFormat formatter = new SimpleDateFormat( "MMM dd yyyy" );        final Display display = new Display();        Shell shell = new Shell( display );        FillLayout layout = new FillLayout();        shell.setLayout( layout );        final Text t = new Text( shell, SWT.BORDER );        Button b = new Button( shell, SWT.PUSH );        b.setText( "Change Date" );        b.addListener( SWT.Selection, new Listener() {            @Override            public void handleEvent( Event event ) {                final SWTCalendarDialog cal = new SWTCalendarDialog( display );                cal.addDateChangedListener( new SWTCalendarListener() {                    @Override                    public void dateChanged( SWTCalendarEvent calendarEvent ) {                        t.setText( formatter.format( calendarEvent.getCalendar().getTime() ) );                    }                } );                if ( t.getText() != null && t.getText().length() > 0 ) {                    try {                        Date d = formatter.parse( t.getText() );                        cal.setDate( d );                    } catch ( ParseException pe ) {                    }                }                cal.open();            }        } );//        CalendarCombo cc = new CalendarCombo( shell, SWT.READ_ONLY | SWT.FLAT);//        cc.setDate( formatter.parse( "August 01 2007" ) );//        //        CDateTime cdt = new CDateTime( shell, CDT.BORDER | CDT.DATE_MEDIUM | CDT.SIMPLE );//        cdt.setSelection( formatter.parse( "August 01 2007" ) );                shell.open();        shell.pack();        while ( !shell.isDisposed() ) {            if ( !display.readAndDispatch() )                display.sleep();        }        display.dispose();    }}